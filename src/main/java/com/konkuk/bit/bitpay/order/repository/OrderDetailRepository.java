package com.konkuk.bit.bitpay.order.repository;

import com.konkuk.bit.bitpay.order.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}
