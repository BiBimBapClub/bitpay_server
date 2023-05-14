package com.konkuk.bit.bitpay.tablehistory.service;

import com.konkuk.bit.bitpay.order.domain.Order;
import com.konkuk.bit.bitpay.table.TableDto;
import com.konkuk.bit.bitpay.tablehistory.domain.TableHistory;
import java.util.List;
import java.util.Optional;

public interface TableHistoryService {

    boolean createOrderHistory(Order order);
    boolean createTableHistory(TableDto tableDto, String type);
    List<TableHistory> getHistories(Integer tableNumber);
    Optional<TableHistory> getHistory(Integer tableNumber);
}
