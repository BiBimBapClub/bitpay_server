package com.konkuk.bit.bitpay.table.service;

import com.konkuk.bit.bitpay.order.dto.OrderDto;
import com.konkuk.bit.bitpay.order.repository.OrderRepository;
import com.konkuk.bit.bitpay.order.service.OrderService;
import com.konkuk.bit.bitpay.table.repository.TableRedisRepository;
import com.konkuk.bit.bitpay.table.domain.TableStatus;
import com.konkuk.bit.bitpay.table.domain.Table;
import com.konkuk.bit.bitpay.table.dto.TableDto;
import com.konkuk.bit.bitpay.tablehistory.service.TableHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class TableServiceImpl implements TableService{

    private final TableRedisRepository tableRepository;
    private final TableHistoryService tableHistoryService;
    private final OrderRepository orderRepository;
    private static final int MAX_TABLE_NUMBER = 35;
    private static final LocalTime INIT_TIME = LocalTime.of(2, 0);

    // 테이블 생성
    // 상태에 따라 테이블 생성하는 것 예외처리도 해야하나;;;
    @Override
    @Transactional
    public TableDto createTable(Integer tableNumber) {
        String key = generateRedisKey(tableNumber);
        Optional<Table> tableOptional = tableRepository.findById(key);
        TableDto tableDto = new TableDto();
        // 이미 해당 테이블 번호에 대한 정보가 Redis에 존재하는 경우
        if (tableOptional.isPresent()) {
            Table table = tableOptional.get();
            // 테이블이 청소완료가 아니면 에외
            if(!table.getStatus().contentEquals(TableStatus.CLEAN.getStatus())) throw new IllegalStateException();

            //사용중
            table.setStatus(TableStatus.ACTIVE.getStatus());
            table.setUuid(UUID.randomUUID());
            tableDto = convertToTableDto(table);
        } else {
            // 해당 테이블 번호에 대한 정보가 Redis에 존재하지 않는 경우
            Table table = new Table();
            table.setNumber(key);
            table.setStatus(TableStatus.ACTIVE.getStatus());
            table.setUuid(UUID.randomUUID());
            tableRepository.save(table);
            tableDto = convertToTableDto(table);
        }
        tableHistoryService.createTableHistory(tableDto, "STATUS");
        return tableDto;
    }

    @Override
    public TableDto getTable(Integer tableNumber) {
        String key = generateRedisKey(tableNumber);
        Table table = tableRepository.findById(key).orElseThrow(IllegalAccessError::new);

        return convertToTableDto(table);
    }

    // 테이블 상태 변경
    // 테이블의 어떤 상태에 따른 변경처리나 예외처리가 필요할 수 있음.
    @Override
    @Transactional
    public TableDto updateTableStatus(Integer tableNumber, String newStatus) {
        String key = generateRedisKey(tableNumber);
        Table table = tableRepository.findById(key).orElseThrow(IllegalAccessError::new);

        if (isValidTableStatus(newStatus)) {
            TableStatus status = TableStatus.valueOf(newStatus);
            table.setStatus(status.getStatus());

        }
        TableDto tableDto = convertToTableDto(table);
        tableHistoryService.createTableHistory(tableDto, "STATUS");
        return tableDto;
    }

//    @Transactional
//    public TableDto updateTableTime(Integer tableNumber, LocalDateTime interval) {
//        String key = generateRedisKey(tableNumber);
//        Table table = tableRepository.findByNumber(key).orElseThrow(IllegalAccessError::new);
//
//        LocalDateTime updatedTime = table.getUpdatedTime();
//        LocalDateTime newTime = updatedTime.plusHours(interval.getHour())
//                .plusMinutes(interval.getMinute());
//        table.setUpdatedTime(newTime);
//
//        TableDto tableDto = convertToTableDto(table);
//        tableHistoryService.createTableHistory(tableDto, "TIME");
//        return tableDto;
//    }

    // 테이블 옮기기
//    @Transactional
//    public TableDto moveTable(Integer tableNumber, Integer newTableNumber) {
//        String sourceKey = generateRedisKey(tableNumber);
//        String destinationKey = generateRedisKey(newTableNumber);
//        Table table = redisTemplate.opsForValue().get(sourceKey);
//        if (table != null) {
//            redisTemplate.opsForValue().set(destinationKey, table);
//            TableDto tableDto = convertToTableDto(table);
//            resetTable(table);
//            return tableDto;
//        }
//        //예외 처리 해야함.
//        return null;
//    }


    @Override
    @Transactional
    public Boolean createOrderToTable(Integer tableNumber, Long orderId) {
        String key = generateRedisKey(tableNumber);
        Table table = tableRepository.findById(key).orElseThrow(IllegalAccessError::new);
        if (table.getOrders().contains(orderId)) {
            return false;
        }
        table.getOrders().add(orderId);
        return true;
    }

    @Override
    @Transactional
    public Boolean deleteOrderToTable(Integer tableNumber, Long orderId) {
        String key = generateRedisKey(tableNumber);
        Table table = tableRepository.findById(key).orElseThrow(IllegalAccessError::new);
        if(table.getOrders().contains(orderId))
            table.getOrders().remove(orderId);
        else
            return false;

        return true;
    }

    @Override
    public List<TableDto> getTableList() {
        List<TableDto> tableList = new ArrayList<>();
        for (int tableNumber = 1; tableNumber <= MAX_TABLE_NUMBER; tableNumber++) {
            String key = generateRedisKey(tableNumber);
            Optional<Table> optionalTable = tableRepository.findById(String.valueOf(tableNumber));
            optionalTable.ifPresent(table -> tableList.add(convertToTableDto(table)));
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
        Integer value = Integer.parseInt(tableDto.getNumber().substring("table:".length()));
        List<OrderDto> orderDtoList = orderRepository.findAllByTableNumber(value).stream()
                .map(o -> new OrderDto(o))
                .collect(Collectors.toList());
        tableDto.setOrders(orderDtoList);
        return tableDto;
    }

    @Override
    @Transactional
    public TableDto confirmCleaned(Integer tableNumber) {
        String key = generateRedisKey(tableNumber);
        Table table = tableRepository.findById(key).orElseThrow(IllegalAccessError::new);

        if(!table.getStatus().contentEquals(TableStatus.CLEAN_REQUEST.getStatus())) throw new IllegalStateException();

        table.setStatus(TableStatus.CLEAN.getStatus());

        return convertToTableDto(table);
    }

    @Override
    @Transactional
    public TableDto confirmClean(Integer tableNumber) {
        String key = generateRedisKey(tableNumber);
        Table table = tableRepository.findById(key).orElseThrow(IllegalAccessError::new);

        if(!table.getStatus().contentEquals(TableStatus.ACTIVE.getStatus())) throw new IllegalStateException();

        table.setStatus(TableStatus.CLEAN_REQUEST.getStatus());

        return convertToTableDto(table);
    }

    @Override
    @Transactional
    public TableDto confirmActive(Integer tableNumber) {
        String key = generateRedisKey(tableNumber);
        Table table = tableRepository.findById(key).orElseThrow(IllegalAccessError::new);

        if(!table.getStatus().contentEquals(TableStatus.CLEAN.getStatus())) throw new IllegalStateException();

        table.setStatus(TableStatus.ACTIVE.getStatus());

        return convertToTableDto(table);
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
