package com.konkuk.bit.bitpay.order.repository;

import com.konkuk.bit.bitpay.order.domain.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByTableNumber(Integer tableNumber);
    List<Order> findAllByTableNumberAndStatus(Integer tableNumber, String status);
}
