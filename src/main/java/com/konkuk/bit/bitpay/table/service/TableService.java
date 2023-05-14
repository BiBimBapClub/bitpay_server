package com.konkuk.bit.bitpay.table.service;

import com.konkuk.bit.bitpay.table.dto.TableDto;

import java.util.List;

public interface TableService {

    TableDto createTable(Integer tableNumber);

    TableDto getTable(Integer tableNumber);

    TableDto updateTableStatus(Integer tableNumber, String newStatus);

//    TableDto updateTableTime(Integer tableNumber, LocalDateTime interval);

//    TableDto moveTable(Integer tableNumber, Integer newTableNumber);

    List<TableDto> getTableList();

    TableDto confirmCleaned(Integer tableNumber);
    TableDto confirmClean(Integer tableNumber);
    TableDto confirmActive(Integer tableNumber);


    Boolean createOrderToTable(Integer tableNumber, Long orderId);
    Boolean deleteOrderToTable(Integer tableNumber, Long orderId);

}