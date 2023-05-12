package com.konkuk.bit.bitpay.order;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @GetMapping("/")
    public void getOrderList(@RequestParam(required = true) Long tableNumber, @RequestParam(required = false) String status) {
        //  해당 테이블에서 들어온 주문 리스트
    }

    @GetMapping("/totalList")
    public void getTotalOrderList() {

    }

    @GetMapping("/{order_id}")
    public void getOrderById(@PathVariable Long order_id) {

    }

    @PostMapping("/")
    public void createOrder(@ModelAttribute Order order) {

    }

    @PostMapping("/{order_id}")
    public void updateOrderById(@PathVariable Long order_id) {

    }
}
