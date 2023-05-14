package com.konkuk.bit.bitpay.table;

import java.time.LocalDateTime;
import java.util.List;

public interface TableService {

    TableDto createTable(Integer tableNumber);

    TableDto getTable(Integer tableNumber);

    TableDto updateTableStatus(Integer tableNumber, String newStatus);

    TableDto updateTableTime(Integer tableNumber, LocalDateTime interval);

    TableDto moveTable(Integer tableNumber, Integer newTableNumber);

    List<TableDto> getTableList();
}
