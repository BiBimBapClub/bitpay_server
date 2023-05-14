package com.konkuk.bit.bitpay.tablehistory.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.konkuk.bit.bitpay.menu.Menu;
import com.konkuk.bit.bitpay.menu.MenuRedisRepository;
import com.konkuk.bit.bitpay.order.dto.OrderCreateDto;
import com.konkuk.bit.bitpay.order.service.OrderService;
import com.konkuk.bit.bitpay.table.domain.Table;
import com.konkuk.bit.bitpay.table.repository.TableRedisRepository;
import com.konkuk.bit.bitpay.table.service.TableService;
import com.konkuk.bit.bitpay.tablehistory.dto.TableHistoryDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class TableHistoryServiceTest {

    @Autowired
    private MenuRedisRepository menuRedisRepository;
    @Autowired
    private TableRedisRepository tableRedisRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private TableHistoryService tableHistoryService;
    @Autowired
    private TableService tableService;

    @BeforeEach
    void beforeEach() {
        Menu menu = new Menu(1L, 1000, "음료수", 10, true);
        menuRedisRepository.save(menu);
    }

    @Test
    @DisplayName("Create History By Order = [Success]")
    public void test1() throws Exception {
        //given
        Map<Long, Integer> orderDetail = new HashMap<>();
        orderDetail.put(1L, 5);

        OrderCreateDto dto = new OrderCreateDto();
        dto.setTableNumber(1);
        dto.setOrderDetail(orderDetail);

        //when
        orderService.createOrder(dto);

        //then
        List<TableHistoryDto> histories = tableHistoryService.getHistories(1);
        System.out.println(histories.get(0).toString());
        assertThat(histories.get(0).getTableNumber()).isEqualTo(1);
    }

    @Test
    @DisplayName("Create History By Table = [Success]")
    public void test2() throws Exception {
        //given
        tableService.createTable(1);

        //when
        tableService.updateTableStatus(1, "사용중");

        //then
        List<TableHistoryDto> histories = tableHistoryService.getHistories(1);
        System.out.println(histories.get(1).toString());
        assertThat(histories.get(1).getType()).isEqualTo("TABLE");
    }
}