package com.konkuk.bit.bitpay.table;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Admin Page 에서 지금까지 주문 로그를 요청하기 위한 Controller */
@Slf4j
@RestController
@RequestMapping("/admin/orders")
public class TableHistoryController {

    //테이블 주문 기록 저장하기
    //해당 테이블에 대한 주문 기록 로그 가져오기
}
