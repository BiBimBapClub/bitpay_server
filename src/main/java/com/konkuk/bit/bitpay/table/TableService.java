package com.konkuk.bit.bitpay.table;

import java.util.List;
import java.util.UUID;

public interface TableService {

    TableDto createTable(Integer tableNumber);

    TableDto getTable(Integer tableNumber);

    TableDto updateTableStatus(Integer tableNumber, String newStatus);

    TableDto moveTable(Integer tableNumber, Integer newTableNumber);

    List<TableDto> getTableList();
}
