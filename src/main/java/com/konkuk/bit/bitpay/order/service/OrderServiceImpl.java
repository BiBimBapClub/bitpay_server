package com.konkuk.bit.bitpay.order.service;

import com.konkuk.bit.bitpay.menu.domain.Menu;
import com.konkuk.bit.bitpay.order.domain.Order;
import com.konkuk.bit.bitpay.order.domain.OrderDetail;
import com.konkuk.bit.bitpay.order.dto.OrderCreateDto;
import com.konkuk.bit.bitpay.order.repository.OrderDetailRepository;
import com.konkuk.bit.bitpay.order.repository.OrderRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    @Override
    @Transactional
    public Optional<Order> createOrder(OrderCreateDto dto) {

        Order order = Order.builder()
                .status(Order.STATUS_PREPARING)
                .timestamp(LocalDateTime.now())
                .tableNumber(dto.getTableNumber())
                .build();

        Map<Long, Integer> orderList = dto.getOrderDetail();

        List<OrderDetail> detailList = new ArrayList<>();

        int totalPrice = 0;
        for (Long menuId : orderList.keySet()) {
            // TODO : MENU 받아오기
            // TODO : MENU getter 요청
            Menu menu = Menu.builder().number(  menuId).price(5000).build();
            Integer quantity = orderList.get(menuId);
            OrderDetail orderDetail = OrderDetail.builder()
                    .menuId(menu.getNumber())
                    .order(order)
                    .quantity(quantity)
                    .build();

            detailList.add(orderDetailRepository.save(orderDetail));
            totalPrice += quantity * 5000;
        }

        order.setDetailList(detailList);
        order.setTotalPrice(totalPrice);

        return Optional.of(orderRepository.save(order));
    }

    @Override
    @Transactional
    public Optional<Order> updateOrderStatusWithPayment(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        // 1. 오더가 없을 때
        if (optionalOrder.isEmpty())
            return Optional.empty();
        // 2. 오더의 상태가 BEFORE_PAYMENT 가 아닐 때
        Order order = optionalOrder.get();
        if (!order.getStatus().equals(Order.STATUS_BEFORE_PAYMENT))
            throw new IllegalStateException("Invalid Request");

        order.setStatus(Order.STATUS_PREPARING);
        return Optional.of(order);
    }

    @Override
    @Transactional
    public Optional<Order> updateOrderStatusWithComplete(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        // 1. 오더가 없을 때
        if (optionalOrder.isEmpty())
            return Optional.empty();
        // 2. 오더의 상태가 PREPARING 이 아닐 때
        Order order = optionalOrder.get();
        if (!order.getStatus().equals(Order.STATUS_PREPARING))
            throw new IllegalStateException("Invalid Request");

        order.setStatus(Order.STATUS_COMPLETE);
        return Optional.of(order);
    }

    @Override
    @Transactional
    public boolean cancelOrder(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isEmpty()) return false;

        Order order = optionalOrder.get();
        orderRepository.delete(order);
        // TODO : table 에서도 지워주길 바람

        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> getOrderListByTableNumber(Integer tableNumber) {
        return orderRepository.findAllByTableNumber(tableNumber).stream()
                .sorted(Comparator.comparing(Order::getTimestamp))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> getOrderListByTableNumberAndStatus(Integer tableNumber, String status) {
        return orderRepository.findAllByTableNumberAndStatus(tableNumber, status).stream()
                .sorted(Comparator.comparing(Order::getTimestamp))
                .collect(Collectors.toList());
    }
}
