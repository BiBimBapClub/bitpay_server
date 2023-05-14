package com.konkuk.bit.bitpay.order;


import com.konkuk.bit.bitpay.order.repository.OrderRepository;
import com.konkuk.bit.bitpay.order.service.OrderService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class ServiceTest {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    @DisplayName("주문 생성 테스트 - [성공]")
    void create_test_01() {

    }

    @Test
    @DisplayName("주문 결제 완료 테스트 - [실패]상태 오류")
    void update_test_01() {

    }

    @Test
    @DisplayName("주문 결제 완료 테스트 - [실패]존재하지 않는 주문")
    void update_test_02() {

    }

    @Test
    @DisplayName("주문 결제 완료 테스트 - [성공]")
    void update_test_03() {

    }

    @Test
    @DisplayName("주문 서빙 완료 테스트 - [실패]상태 오류")
    void update_test_04() {

    }

    @Test
    @DisplayName("주문 서빙 완료 테스트 - [실패]존재하지 않는 주문")
    void update_test_05() {

    }

    @Test
    @DisplayName("주문 서빙 완료 테스트 - [성공]")
    void update_test_06() {

    }

    @Test
    @DisplayName("주문 취소 테스트 - [실패]존재하지 않는 주문")
    void cancel_test_01() {

    }

    @Test
    @DisplayName("주문 취소 테스트 - [성공]")
    void cancel_test_02() {

    }

    @Test
    @DisplayName("주문 리스트 테스트 - [테이블 번호]")
    void list_test_01() {

    }

    @Test
    @DisplayName("주문 리스트 테스트 - [테이블 번호 + 상태]")
    void list_test_02() {

    }

}
