package com.konkuk.bit.bitpay.order.controller;

import com.konkuk.bit.bitpay.order.domain.Order;
import com.konkuk.bit.bitpay.order.dto.OrderCreateDto;
import com.konkuk.bit.bitpay.order.dto.OrderDto;
import com.konkuk.bit.bitpay.order.service.OrderService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    private void setResponse(HttpServletResponse response, int sc, String msg) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setStatus(sc);
        response.getWriter().write(msg);
    }

    @GetMapping
    public List<OrderDto> getOrderList(
            @RequestParam(required = false) Integer tableNumber,
            @RequestParam(required = false, defaultValue = Order.STATUS_ALL) String status,
            HttpServletResponse response) {
        if (tableNumber == null && !status.equals(Order.STATUS_ALL)) {
            return orderService.getOrderListByStatus(status).stream()
                    .map(order -> new OrderDto(order))
                    .collect(Collectors.toList());
        } else if (tableNumber != null && status.equals(Order.STATUS_ALL)) {
            return orderService.getOrderListByTableNumber(tableNumber).stream()
                    .map(order -> new OrderDto(order))
                    .collect(Collectors.toList());
        } else if (tableNumber == null && status.equals(Order.STATUS_ALL)) {
            return orderService.getOrderList().stream()
                    .map(order -> new OrderDto(order))
                    .collect(Collectors.toList());
        }

        return orderService.getOrderListByTableNumberAndStatus(tableNumber, status).stream()
                .map(order -> new OrderDto(order))
                .collect(Collectors.toList());
    }

    @PostMapping
    public OrderDto createOrder(
            @RequestBody OrderCreateDto dto,
            HttpServletResponse response) throws IOException {
        try {
            Optional<Order> order = orderService.createOrder(dto);
            if (order.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return null;
            }
            return new OrderDto(order.get());
        } catch (IllegalStateException e) {
            setResponse(response, HttpServletResponse.SC_BAD_REQUEST, "주문 실패 : 주문한 메뉴 중 주문 불가(수량 부족)한 메뉴 존재");
            return null;
        } catch (IllegalArgumentException e) {
            setResponse(response, HttpServletResponse.SC_BAD_REQUEST, "주문 실패 : " + e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/{orderId}")
    public void cancelOrder(
            @PathVariable Long orderId,
            HttpServletResponse response) throws IOException {
        if (orderService.cancelOrder(orderId)) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            return;
        }
        setResponse(response, HttpServletResponse.SC_BAD_REQUEST, "취소 실패 : 주문 정보를 확인하세요");
        return;
    }


    @GetMapping("/confirm_payment/{orderId}")
    public OrderDto updateOrderStatusWithPayment(
            @PathVariable Long orderId,
            HttpServletResponse response
    ) throws IOException {
        try {
            Optional<Order> orderOptional = orderService.updateOrderStatusWithPayment(orderId);

            if (orderOptional.isEmpty()) {
                setResponse(response, HttpServletResponse.SC_NOT_FOUND, "결제 완료 실패 : 없는 주문 id");
                return null;
            }

            Order order = orderOptional.get();
            return new OrderDto(order);
        } catch (IllegalStateException e) {
            setResponse(response, HttpServletResponse.SC_BAD_REQUEST, "결제 완료 실패 : 주문 상태를 확인하세요");
            return null;
        }
    }

    @GetMapping("/confirm_complete/{orderId}")
    public OrderDto updateOrderStatusWithComplete(
            @PathVariable Long orderId,
            HttpServletResponse response
    ) throws IOException {
        try {
            Optional<Order> orderOptional = orderService.updateOrderStatusWithComplete(orderId);

            if (orderOptional.isEmpty()) {
                setResponse(response, HttpServletResponse.SC_NOT_FOUND, "서빙 완료 실패 : 없는 주문 id");
                return null;
            }

            Order order = orderOptional.get();
            return new OrderDto(order);
        } catch (IllegalStateException e) {
            setResponse(response, HttpServletResponse.SC_BAD_REQUEST, "서빙 완료 실패 : 주문 상태를 확인하세요");
            return null;
        }
    }
}
