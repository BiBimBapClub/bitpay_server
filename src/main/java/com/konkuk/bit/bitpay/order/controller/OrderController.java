package com.konkuk.bit.bitpay.order.controller;

import com.konkuk.bit.bitpay.order.domain.Order;
import com.konkuk.bit.bitpay.order.dto.OrderCreateDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @GetMapping("/")
    public void getOrderList(
            @RequestParam(required = true) Long tableNumber,
            @RequestParam(required = false, defaultValue = Order.STATUS_ALL) String status) {

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
