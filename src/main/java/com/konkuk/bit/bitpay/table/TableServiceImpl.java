package com.konkuk.bit.bitpay.table;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class TableServiceImpl implements TableService{

    private final RedisTemplate<String, Table> redisTemplate;
    private static final int MAX_TABLE_NUMBER = 35;

    // 테이블 생성
    // 상태에 따라 테이블 생성하는 것 예외처리도 해야하나;;;
    @Override
    public TableDto createTable(Integer tableNumber) {
        String key = generateRedisKey(tableNumber);
        Table table = redisTemplate.opsForValue().get(key);
        if (table != null) {
            // 이미 해당 테이블 번호에 대한 정보가 Redis에 존재하는 경우
            // 테이블이 청소 다하고 받을 준비 되어있을 때로 해야함
            table.setNumber(tableNumber);
            table.setDescription("초기값");
            table.setStatus("초기값");
            table.setUuid(UUID.randomUUID());
        } else {
            // 해당 테이블 번호에 대한 정보가 Redis에 존재하지 않는 경우
            table = new Table();
            table.setNumber(tableNumber);
            table.setDescription("새손님 들어왔습니다. description 여기에는 뭐 넣지");
            table.setStatus("여기에 시간값 넣어놔야하나?");
            table.setUuid(UUID.randomUUID());
            redisTemplate.opsForValue().set(key, table);
        }
        return convertToTableDto(table);
    }

    @Override
    public TableDto getTable(Integer tableNumber) {
        String key = generateRedisKey(tableNumber);
        Table table = redisTemplate.opsForValue().get(key);
        if (table != null) {
            return convertToTableDto(table);
        }
        // 예외처리 해야함.
        return null;
    }

    // 테이블 상태 변경
    // 테이블의 어떤 상태에 따른 변경처리나 예외처리가 필요할 수 있음.
    @Override
    public TableDto updateTableStatus(Integer tableNumber, String newStatus) {
        String key = generateRedisKey(tableNumber);
        Table table = redisTemplate.opsForValue().get(key);
        if (table != null) {
            table.setStatus(newStatus);
            redisTemplate.opsForValue().set(key, table);
            return convertToTableDto(table);
        }
        // 예외처리 해야함.
        return null;
    }

    // 테이블 옮기기
    @Override
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
        tableDto.setDescription(table.getDescription());
        tableDto.setUpdatedTime(table.getUpdatedTime());
        return tableDto;
    }

    // 완전 초기값 설정해줘야함.
    private void resetTable(Table table) {
        table.setUpdatedTime(null);
        table.setUuid(null);
        table.setStatus("초기값");
        table.setDescription("초기값");
    }
}
