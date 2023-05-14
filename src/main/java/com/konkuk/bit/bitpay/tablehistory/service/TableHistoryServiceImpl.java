package com.konkuk.bit.bitpay.tablehistory.service;

import com.konkuk.bit.bitpay.order.domain.Order;
import com.konkuk.bit.bitpay.table.TableService;
import com.konkuk.bit.bitpay.table.TableServiceImpl;
import com.konkuk.bit.bitpay.tablehistory.domain.TableHistory;
import com.konkuk.bit.bitpay.tablehistory.repository.TableHistoryRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TableHistoryServiceImpl implements TableHistoryService {

    private final TableHistoryRepository tableHistoryRepository;
    private final TableService tableService;

    //Order가 들어왔을 때 해당 Order를 TableHistory에 반영하고, 해당 table의 상태도 반영
    @Override
    @Transactional
    public boolean createOrderHistory(Order order) {
        Integer tableNumber = order.getTableNumber();

        //TODO: table의 상태 변경


        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TableHistory> getHistories(Integer tableNumber) {
       return tableHistoryRepository.findTableHistoriesByTableNumberOrderByTimestampDesc(tableNumber);
    }

    @Override
    public Optional<TableHistory> getHistory(Integer tableNumber) {
        return Optional.empty();
    }
}
