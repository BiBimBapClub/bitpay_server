package com.konkuk.bit.bitpay.table;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TableInitializer implements CommandLineRunner {

    private final TableRedisRepository tableRepository;

    private static final int MAX_TABLE_NUMBER = 35;


    @Override
    @Transactional
    public void run(String... args) throws Exception {

        for (int tableNumber = 1; tableNumber <= MAX_TABLE_NUMBER; tableNumber++) {
            String key = generateRedisKey(tableNumber);
            Table table = Table.builder()
                    .number(key)
                    .uuid(UUID.randomUUID())
                    .status(TableStatus.CLEAN.getStatus())
                    .updatedTime(LocalDateTime.now())
                    .build();
            tableRepository.save(table);
        }
    }

    private String generateRedisKey(Integer tableNumber) {
        return "table:" + tableNumber;
    }
}