package com.konkuk.bit.bitpay.table;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tables")
@RequiredArgsConstructor
public class TableController {

    private final TableServiceImpl tableService;


    @GetMapping("/")
    public List<TableDto> getTableList() {
        return tableService.getTableList();
    }

    @GetMapping("/{tableNumber}")
    public TableDto getTable(@PathVariable Integer tableNumber) {
        return tableService.getTable(tableNumber);
    }

    @PostMapping("/{tableNumber}")
    public TableDto createTable(@PathVariable Integer tableNumber) {
        return tableService.createTable(tableNumber);
    }

    @PostMapping("/{tableNumber}/status")
    public TableDto changeTableStatus(@PathVariable Integer tableNumber, @RequestParam String status) {
        return tableService.updateTableStatus(tableNumber, status);
    }

//    테이블 상태변경 {"청소완료"),("청소요청"),("사용중")}
}

//    @PostMapping("/{tableNumber}/move")
//    public TableDto moveTable(@PathVariable Integer tableNumber, @RequestParam Integer newTableNumber) {
//        return tableService.moveTable(tableNumber, newTableNumber);
//    }