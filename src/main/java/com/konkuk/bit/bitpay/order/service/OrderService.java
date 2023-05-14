package com.konkuk.bit.bitpay.order.service;

import com.konkuk.bit.bitpay.order.domain.Order;
import com.konkuk.bit.bitpay.order.dto.OrderCreateDto;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    // Order Create
    Optional<Order> createOrder(OrderCreateDto dto);

    // Order Status Update
    Optional<Order> updateOrderStatusWithPayment(Long orderId);
    Optional<Order> updateOrderStatusWithComplete(Long orderId);

    // Order Cancel
    boolean cancelOrder(Long orderId);

    // Order List
    List<Order> getOrderListByTableNumber(Integer tableNumber);
    List<Order> getOrderListByTableNumberAndStatus(Integer tableNumber, String status);
}
