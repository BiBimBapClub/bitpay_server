package com.konkuk.bit.bitpay.order.controller;

import com.konkuk.bit.bitpay.order.domain.Order;
import com.konkuk.bit.bitpay.order.dto.OrderCreateDto;
import com.konkuk.bit.bitpay.order.dto.OrderDto;
import com.konkuk.bit.bitpay.order.service.OrderService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

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
        }
        return orderService.getOrderListByTableNumberAndStatus(tableNumber, status).stream()
                .map(order -> new OrderDto(order))
                .collect(Collectors.toList());
    }

    @PostMapping
    public OrderDto createOrder(
            @RequestBody OrderCreateDto dto,
            HttpServletResponse response) {
        Optional<Order> order = orderService.createOrder(dto);
        if (order.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        return new OrderDto(order.get());
    }

    @DeleteMapping("/{orderId}")
    public void cancelOrder(
            @PathVariable Long orderId,
            HttpServletResponse response
    ) {
        if (orderService.cancelOrder(orderId)) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            return;
        }
        response.setStatus(HttpServletResponse.SC_CONFLICT);
        return;
    }


    @GetMapping("/confirm_payment/{orderId}")
    public OrderDto updateOrderStatusWithPayment(
            @PathVariable Long orderId,
            HttpServletResponse response
    ) {
        try {
            Optional<Order> orderOptional = orderService.updateOrderStatusWithPayment(orderId);

            if (orderOptional.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return null;
            }

            Order order = orderOptional.get();
            return new OrderDto(order);
        } catch (IllegalStateException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }

    @GetMapping("/confirm_complete/{orderId}")
    public OrderDto updateOrderStatusWithComplete(
            @PathVariable Long orderId,
            HttpServletResponse response
    ) {
        try {
            Optional<Order> orderOptional = orderService.updateOrderStatusWithComplete(orderId);

            if (orderOptional.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return null;
            }

            Order order = orderOptional.get();
            return new OrderDto(order);
        } catch (IllegalStateException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }
}
