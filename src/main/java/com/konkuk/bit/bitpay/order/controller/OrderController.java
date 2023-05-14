package com.konkuk.bit.bitpay.order.controller;

import com.konkuk.bit.bitpay.order.domain.Order;
import com.konkuk.bit.bitpay.order.dto.OrderCreateDto;
import com.konkuk.bit.bitpay.order.dto.OrderDto;
import com.konkuk.bit.bitpay.order.service.OrderService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/")
    public List<OrderDto> getOrderList(
            @RequestParam(required = true) Integer tableNumber,
            @RequestParam(required = false, defaultValue = Order.STATUS_ALL) String status,
            HttpServletResponse response) {
        if(tableNumber==null) {
            return null;
        }
        if (status.equals(Order.STATUS_ALL)) {
            return orderService.getOrderListByTableNumber(tableNumber).stream()
                    .map(order->new OrderDto(order))
                    .collect(Collectors.toList());
        }
        return orderService.getOrderListByTableNumberAndStatus(tableNumber,status).stream()
                .map(order -> new OrderDto(order))
                .collect(Collectors.toList());
    }

    @GetMapping("/totalList")
    public void getTotalOrderList() {

    }

    @GetMapping("/{order_id}")
    public void getOrderById(@PathVariable Long order_id) {

    }

    @PostMapping("/")
    public void createOrder(@RequestBody OrderCreateDto dto) {

    }

    @GetMapping("/confirm_payment/{order_id}")
    public void updateOrderStatusWithPayment(@PathVariable Long order_id) {

    }

    @GetMapping("/confirm_complete/{order_id}")
    public void updateOrderStatusWithComplete(@PathVariable Long order_id) {

    }
}
