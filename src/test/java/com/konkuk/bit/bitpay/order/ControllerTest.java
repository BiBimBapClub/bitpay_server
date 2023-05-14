package com.konkuk.bit.bitpay.order;

import com.konkuk.bit.bitpay.menu.Menu;
import com.konkuk.bit.bitpay.menu.MenuRedisRepository;
import com.konkuk.bit.bitpay.order.repository.OrderRepository;
import com.konkuk.bit.bitpay.order.service.OrderService;
import com.konkuk.bit.bitpay.table.Table;
import com.konkuk.bit.bitpay.table.TableRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class ControllerTest {
    @Autowired
    MenuRedisRepository menuRedisRepository;

    @Autowired
    TableRedisRepository tableRedisRepository;

    @Autowired
    OrderRepository orderRepository;

    void addMenu() {
        for (int i = 1; i <= 10; i++) {
            menuRedisRepository.save(new Menu(Long.valueOf(i), i * 1000, "메뉴" + i, 10, true));
        }
    }

    void addTable() {
        for (int i = 1; i <= 10; i++) {
            tableRedisRepository.save(new Table(
                    Long.valueOf(i),


            ))
        }
    }

}
