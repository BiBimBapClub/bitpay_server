package com.konkuk.bit.bitpay.tablehistory.service;

import com.konkuk.bit.bitpay.menu.service.MenuService;
import com.konkuk.bit.bitpay.order.domain.Order;
import com.konkuk.bit.bitpay.order.domain.OrderDetail;
import com.konkuk.bit.bitpay.table.dto.TableDto;
import com.konkuk.bit.bitpay.tablehistory.domain.TableHistory;
import com.konkuk.bit.bitpay.tablehistory.dto.TableHistoryDto;
import com.konkuk.bit.bitpay.tablehistory.repository.TableHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TableHistoryServiceImpl implements TableHistoryService {

    private final TableHistoryRepository tableHistoryRepository;
    private final MenuService menuService;

    //Order가 들어왔을 때 해당 Order를 TableHistory에 반영
    @Override
    @Transactional
    public boolean createOrderHistory(Order order) {
        Integer tableNumber = order.getTableNumber();

        //description에 들어가야 하는 것: 주문한거, 총 주문금액
        StringBuilder description = new StringBuilder();

        List<OrderDetail> detailList = order.getDetailList();
        for (OrderDetail orderDetail : detailList) {
            Long menuId = orderDetail.getMenuId();
            String menu = menuService.getMenuEntity(menuId).getName();
            Integer quantity = orderDetail.getQuantity();

            //menu1: n개, menu2: n개
            description.append(menu).append(": ").append(quantity).append("개, ");
        }
        description.append("총 주문금액: ").append(order.getTotalPrice()).append("\n");

        TableHistory tableHistory = TableHistory.builder()
                .tableNumber(tableNumber)
                .description(description.toString())
                .type("ORDER")
                .build();

        tableHistoryRepository.save(tableHistory);

        return true;
    }

    @Override
    @Transactional
    public boolean createTableHistory(TableDto tableDto, String type) {
        String tableNumber = tableDto.getNumber();
        tableNumber = tableNumber.replace("table:", "");

        //description에 들어가야 하는 것: table 상태 변경 내용, table 이용 시간 추가
        StringBuilder description = new StringBuilder();

        if (type.equals("TIME")) {
            description.append("주문으로 인한 시간 증가").append(tableDto.getUpdatedTime()).append("\n");

            TableHistory tableHistory = TableHistory.builder()
                    .tableNumber(Integer.valueOf(tableNumber))
                    .type("TABLE")
                    .description(description.toString())
                    .build();

            tableHistoryRepository.save(tableHistory);
        } else if (type.equals("STATUS")) {
            description.append("테이블 상태 변경 => ").append(tableDto.getStatus()).append("\n");

            TableHistory tableHistory = TableHistory.builder()
                    .tableNumber(Integer.valueOf(tableNumber))
                    .type("TABLE")
                    .description(description.toString())
                    .build();

            tableHistoryRepository.save(tableHistory);
        }

        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TableHistoryDto> getHistories(Integer tableNumber) {
        List<TableHistory> histories = tableHistoryRepository.findTableHistoriesByTableNumberOrderByTimestampDesc(
                tableNumber);

        List<TableHistoryDto> tableHistoryDtos = new ArrayList<>();
        for (TableHistory history : histories) {
            tableHistoryDtos.add(new TableHistoryDto(history));
        }

        return tableHistoryDtos;
    }

    @Override
    public Optional<TableHistory> getHistory(Integer tableNumber) {
        return Optional.empty();
    }
}
