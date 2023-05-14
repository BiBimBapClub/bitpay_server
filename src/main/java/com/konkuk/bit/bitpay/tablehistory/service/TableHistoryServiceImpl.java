package com.konkuk.bit.bitpay.tablehistory.service;

import com.konkuk.bit.bitpay.order.domain.Order;
import com.konkuk.bit.bitpay.order.domain.OrderDetail;
import com.konkuk.bit.bitpay.table.TableDto;
import com.konkuk.bit.bitpay.table.TableService;
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
//    private final TableService tableService;

    //Order가 들어왔을 때 해당 Order를 TableHistory에 반영하고, 해당 table의 상태도 반영
    @Override
    @Transactional
    public boolean createOrderHistory(Order order) {
        Integer tableNumber = order.getTableNumber();

        //description에 들어가야 하는 것: 주문한거, 총 주문금액
        StringBuilder description = new StringBuilder();

        List<OrderDetail> detailList = order.getDetailList();
        for (OrderDetail orderDetail : detailList) {

        }

        description.append("총 주문금액: ").append(order.getTotalPrice());

        return false;
    }

    @Override
    public boolean createTableHistory(TableDto tableDto, String type) {
        Integer tableNumber = tableDto.getNumber();

        //description에 들어가야 하는 것: table 상태 변경 내용, table 이용 시간 추가
        StringBuilder description = new StringBuilder();

        if (type.equals("TIME")) {

        } else if (type.equals("STATUS")) {

        }
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
