package com.konkuk.bit.bitpay.tablehistory;

import com.konkuk.bit.bitpay.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TableHistoryService {

    private final TableHistoryRepository tableHistoryRepository;

    //Order가 들어왔을 때 해당 Order를 TableHistory에 반영하고, 해당 table의 상태도 반영
    @Transactional
    public boolean createOrderHistory(Order order) {

    }
}
