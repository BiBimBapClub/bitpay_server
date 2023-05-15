package com.konkuk.bit.bitpay.tablehistory;

import com.konkuk.bit.bitpay.tablehistory.dto.TableHistoryDto;
import com.konkuk.bit.bitpay.tablehistory.service.TableHistoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** Admin Page 에서 지금까지 주문 로그를 요청하기 위한 Controller */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class TableHistoryController {

    private final TableHistoryService tableHistoryService;

    //해당 테이블에 대한 모든 주문 기록 로그 가져오기 (시간 내림차순)
    @GetMapping("/log")
    public List<TableHistoryDto> getTableHistory(@RequestParam Integer tableNumber) {
        return tableHistoryService.getHistories(tableNumber);
    }
}
