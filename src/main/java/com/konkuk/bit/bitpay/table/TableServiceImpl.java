package com.konkuk.bit.bitpay.table;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class TableServiceImpl implements TableService{

    private final RedisTemplate<String, Table> redisTemplate;
    private static final int MAX_TABLE_NUMBER = 35;
    private static final LocalTime INIT_TIME = LocalTime.of(2, 0);

    // 테이블 생성
    // 상태에 따라 테이블 생성하는 것 예외처리도 해야하나;;;
    @Override
    @Transactional
    public TableDto createTable(Integer tableNumber) {
        String key = generateRedisKey(tableNumber);
        Table table = redisTemplate.opsForValue().get(key);

        // 이미 해당 테이블 번호에 대한 정보가 Redis에 존재하는 경우
        if (table != null) {
            // 테이블이 청소완료가 아니면 에외
            if(!table.getStatus().contentEquals(TableStatus.CLEAN.getStatus())) throw new IllegalStateException();

            //사용중
            table.setStatus(TableStatus.ACTIVE.getStatus());
            table.setUuid(UUID.randomUUID());
        } else {
            // 해당 테이블 번호에 대한 정보가 Redis에 존재하지 않는 경우
            table = new Table();
            table.setNumber(tableNumber);
            table.setStatus(TableStatus.ACTIVE.getStatus());
            table.setUuid(UUID.randomUUID());
            redisTemplate.opsForValue().set(key, table);
        }
        return convertToTableDto(table);
    }

    @Override
    public TableDto getTable(Integer tableNumber) {
        String key = generateRedisKey(tableNumber);
        Table table = redisTemplate.opsForValue().get(key);
        if (table == null) throw new IllegalStateException();

        return convertToTableDto(table);
    }

    // 테이블 상태 변경
    // 테이블의 어떤 상태에 따른 변경처리나 예외처리가 필요할 수 있음.
    @Override
    @Transactional
    public TableDto updateTableStatus(Integer tableNumber, String newStatus) {
        String key = generateRedisKey(tableNumber);
        Table table = redisTemplate.opsForValue().get(key);
        if (table == null) throw new IllegalStateException();

        if (isValidTableStatus(newStatus)) {
            TableStatus status = TableStatus.valueOf(newStatus);
            table.setStatus(status.getStatus());
            redisTemplate.opsForValue().set(key, table);
        }

        return convertToTableDto(table);
    }

    @Override
    @Transactional
    public TableDto updateTableTime(Integer tableNumber, LocalDateTime interval) {
        String key = generateRedisKey(tableNumber);
        Table table = redisTemplate.opsForValue().get(key);
        if (table == null) throw new IllegalStateException();
        LocalDateTime updatedTime = table.getUpdatedTime();
        LocalDateTime newTime = updatedTime.plusHours(interval.getHour())
                .plusMinutes(interval.getMinute());
        table.setUpdatedTime(newTime);

        return convertToTableDto(table);
    }

    // 테이블 옮기기
    @Override
    @Transactional
    public TableDto moveTable(Integer tableNumber, Integer newTableNumber) {
        String sourceKey = generateRedisKey(tableNumber);
        String destinationKey = generateRedisKey(newTableNumber);
        Table table = redisTemplate.opsForValue().get(sourceKey);
        if (table != null) {
            redisTemplate.opsForValue().set(destinationKey, table);
            TableDto tableDto = convertToTableDto(table);
            resetTable(table);
            return tableDto;
        }
        //예외 처리 해야함.
        return null;
    }

    @Override
    public List<TableDto> getTableList() {
        List<TableDto> tableList = new ArrayList<>();
        for (int tableNumber = 1; tableNumber <= MAX_TABLE_NUMBER; tableNumber++) {
            String key = generateRedisKey(tableNumber);
            Table table = redisTemplate.opsForValue().get(key);
            if (table != null) {
                tableList.add(convertToTableDto(table));
            }
        }
        return tableList;
    }

    private String generateRedisKey(Integer tableNumber) {
        return "table:" + tableNumber;
    }

    private TableDto convertToTableDto(Table table) {
        TableDto tableDto = new TableDto();
        tableDto.setNumber(table.getNumber());
        tableDto.setUuid(table.getUuid());
        tableDto.setStatus(table.getStatus());
        tableDto.setUpdatedTime(table.getUpdatedTime());
        return tableDto;
    }

    // 완전 초기값 설정해줘야함.
    private void resetTable(Table table) {
        table.setUpdatedTime(null);
        table.setUuid(null);
        table.setStatus(TableStatus.CLEAN.getStatus());
    }

    private boolean isValidTableStatus(String status) {
        try {
            TableStatus.valueOf(status);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
