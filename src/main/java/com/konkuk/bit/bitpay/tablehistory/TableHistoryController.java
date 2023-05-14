package com.konkuk.bit.bitpay.tablehistory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** Admin Page 에서 지금까지 주문 로그를 요청하기 위한 Controller */
@Slf4j
@RestController
@RequestMapping("/admin")
public class TableHistoryController {

    //해당 테이블에 대한 모든 주문 기록 로그 가져오기 (시간 내림차순)
    @GetMapping("/history")
    public ResponseEntity<Void> getTableHistory(@RequestParam Integer tableNumber) {
        return ResponseEntity.ok().build();
    }
}
