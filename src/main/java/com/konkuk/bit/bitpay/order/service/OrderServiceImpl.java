package com.konkuk.bit.bitpay.order.service;

import com.konkuk.bit.bitpay.menu.Menu;
import com.konkuk.bit.bitpay.order.domain.Order;
import com.konkuk.bit.bitpay.order.domain.OrderDetail;
import com.konkuk.bit.bitpay.order.dto.OrderCreateDto;
import com.konkuk.bit.bitpay.order.dto.OrderUpdateDto;
import com.konkuk.bit.bitpay.order.repository.OrderDetailRepository;
import com.konkuk.bit.bitpay.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
            Menu menu = Menu.builder().id(menuId).price(5000).build();
            Integer quantity = orderList.get(menuId);
            OrderDetail orderDetail = OrderDetail.builder()
                    .menu(menu)
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
    public boolean cancelOrder(Long orderId) {
        return false;
    }

    @Override
    public List<Order> getOrderListByTableNumber(Long tableNumber) {
        return null;
    }

    @Override
    public List<Order> getOrderListByTableNumberAndStatus(Long tableNumber, String status) {
        return null;
    }
}
