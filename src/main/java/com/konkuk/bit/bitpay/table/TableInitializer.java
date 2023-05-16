package com.konkuk.bit.bitpay.table;

import com.konkuk.bit.bitpay.order.dto.OrderDto;
import com.konkuk.bit.bitpay.table.domain.Table;
import com.konkuk.bit.bitpay.table.domain.TableStatus;
import com.konkuk.bit.bitpay.table.dto.TableDto;
import com.konkuk.bit.bitpay.table.repository.TableRedisRepository;
import com.konkuk.bit.bitpay.tablehistory.service.TableHistoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TableInitializer implements CommandLineRunner {

    private final TableRedisRepository tableRepository;
    private final TableHistoryServiceImpl tableHistoryService;

    private static final int MAX_TABLE_NUMBER = 45;


    @Override
    @Transactional
    public void run(String... args) throws Exception {

        for (int tableNumber = 1; tableNumber <= MAX_TABLE_NUMBER; tableNumber++) {
            String key = generateRedisKey(tableNumber);
            Table table = Table.builder()
                    .number(key)
                    .uuid(UUID.randomUUID().toString())
                    .status(TableStatus.CLEAN.getStatus())
                    .updatedTime(LocalDateTime.now())
                    .build();
            Table save = tableRepository.save(table);
            TableDto tableDto = convertToTableDto(save);
        }
    }

    private String generateRedisKey(Integer tableNumber) {
        return "table:" + tableNumber;
    }

    private TableDto convertToTableDto(Table table) {
        TableDto tableDto = new TableDto();
        tableDto.setNumber(table.getNumber());
        tableDto.setUuid(table.getUuid().toString());
        tableDto.setStatus(table.getStatus());
        tableDto.setUpdatedTime(table.getUpdatedTime());
        Integer value = Integer.parseInt(tableDto.getNumber().substring("table:".length()));
        return tableDto;
    }
}