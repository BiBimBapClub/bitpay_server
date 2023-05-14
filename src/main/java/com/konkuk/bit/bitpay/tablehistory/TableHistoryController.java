package com.konkuk.bit.bitpay.tablehistory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** Admin Page 에서 지금까지 주문 로그를 요청하기 위한 Controller */
@Slf4j
@RestController
@RequestMapping("/admin")
public class TableHistoryController {

    //테이블 주문 기록 저장하기
    @PostMapping("/create/order")
    public ResponseEntity<Void> restoreOrder() {

    }

    //해당 테이블에 대한 주문 기록 로그 가져오기
    @GetMapping("/history")
    public ResponseEntity<Void> getTableHistory(@RequestParam Integer tabledNumber) {

    }

    //해당 테이블의 상태 바꾸고, 저장하기 (status: 비었음, 결제 진행중, 서빙중, 서빙 완료)
    @PutMapping("/edit")
    public ResponseEntity<Void> editTableStatus() {

    }
}
