package com.konkuk.bit.bitpay.order.service;

import com.konkuk.bit.bitpay.menu.domain.Menu;
import com.konkuk.bit.bitpay.menu.service.MenuService;
import com.konkuk.bit.bitpay.order.domain.Order;
import com.konkuk.bit.bitpay.order.domain.OrderDetail;
import com.konkuk.bit.bitpay.order.dto.OrderCreateDto;
import com.konkuk.bit.bitpay.order.dto.OrderDetailCreateDto;
import com.konkuk.bit.bitpay.order.repository.OrderDetailRepository;
import com.konkuk.bit.bitpay.order.repository.OrderRepository;
import com.konkuk.bit.bitpay.table.domain.TableStatus;
import com.konkuk.bit.bitpay.table.service.TableService;
import com.konkuk.bit.bitpay.tablehistory.service.TableHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MenuService menuService;
    private final TableHistoryService tableHistoryService;
    private final TableService tableService;
    private final OrderDetailRepository orderDetailRepository;

    @Override
    @Transactional
    public Optional<Order> createOrder(OrderCreateDto dto) {

        Integer tableNumber = dto.getTableNumber();
        Order order = Order.builder()
                .status(Order.STATUS_BEFORE_PAYMENT)
                .timestamp(LocalDateTime.now())
                .tableNumber(tableNumber)
                .build();

        List<OrderDetailCreateDto> orderDetailList = dto.getOrderDetail();

        List<OrderDetail> detailList = new ArrayList<>();

        int[][] removeArr = {
                {},
                {8, 9},
                {9},
                {8},
                {9},
                {8},
                {},
                {},
                {8},
                {9},
                {},
                {11},
                {12},
                {13}
        };

        int totalPrice = 0;
        if (orderDetailList == null) {
            throw new IllegalArgumentException("주문 정보가 없음");
        }

        for (OrderDetailCreateDto cdto : orderDetailList) {
            System.out.println(cdto);
            Long menuId = cdto.getMenu_id();
            Integer quantity = cdto.getCount();
            Menu menu = menuService.getMenuEntity(menuId);

            if (!menu.isStatus()) {
                throw new IllegalArgumentException("주문 불가능한 메뉴가 존재합니다.");
            }

            for (int mId : removeArr[Math.toIntExact(menuId)]) {
                if (!menuService.isPossibleOrderQuantity(Long.valueOf(mId), quantity)) {
                    throw new IllegalStateException("주문 불가능한 메뉴가 존재합니다.");
                }
            }

            OrderDetail orderDetail = OrderDetail.builder()
                    .menuId(menuId)
                    .order(order)
                    .quantity(quantity)
                    .build();

            detailList.add(orderDetailRepository.save(orderDetail));
            totalPrice += quantity * menu.getPrice();
        }

        if (tableService.isFirstOrder(tableNumber) && totalPrice < 14000) {
            throw new IllegalArgumentException("첫 주문은 14000원 이상이어야 합니다");
        }

        order.setDetailList(detailList);
        order.setTotalPrice(totalPrice);

        Order saved = orderRepository.save(order);
        tableHistoryService.createOrderHistory(saved);
        tableService.createOrderToTable(tableNumber, saved.getId());
        if (tableService.isFirstOrder(tableNumber)) {
            if (!tableService.getTableStatusActive(tableNumber)) {
                tableService.updateTableStatus(tableNumber, TableStatus.ACTIVE.name());
                tableService.setNowUpdateTableTime(tableNumber);
            }
        }
        return Optional.of(saved);
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
            throw new IllegalStateException("올바르지 않은 주문 상태입니다");

        int[] timeArr = {
                0,
                2,
                2,
                2,
                2,
                2,
                0,
                1,
                1,
                1,
                0,
                0,
                0,
                0
        };
        int flag = 0;


        for (OrderDetail orderDetail : order.getDetailList()) {
            if (!tableService.isFirstOrder(order.getTableNumber()))
                flag = Math.max(flag, timeArr[Math.toIntExact(orderDetail.getMenuId())]);
            if (!menuService.updateMenuRemainStatus(orderDetail.getMenuId(), orderDetail.getQuantity()))
                throw new IllegalStateException("주문 물품 수량 부족");
        }

        order.setStatus(Order.STATUS_PREPARING);
        switch (flag) {
            case 1:
                tableService.updateTableTime(order.getTableNumber(), LocalTime.of(0, 30));
                break;
            case 2:
                tableService.updateTableTime(order.getTableNumber(), LocalTime.of(1, 0));
                break;
            default:
                break;
        }
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
        tableService.deleteOrderToTable(order.getTableNumber(), orderId);
        return true;
    }

    @Override
    public List<Order> getOrderList() {
        return orderRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<Order> getOrderListByStatus(String status) {
        return orderRepository.findAllByStatus(status).stream()
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> getOrderListByTableNumber(Integer tableNumber) {
        return orderRepository.findAllByTableNumber(tableNumber).stream()
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> getOrderListByTableNumberAndStatus(Integer tableNumber, String status) {
        return orderRepository.findAllByTableNumberAndStatus(tableNumber, status).stream()
                .collect(Collectors.toList());
    }
}
