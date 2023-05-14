package com.konkuk.bit.bitpay.order.repository;

import com.konkuk.bit.bitpay.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
