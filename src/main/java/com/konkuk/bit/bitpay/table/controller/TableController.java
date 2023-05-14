package com.konkuk.bit.bitpay.table.controller;

import com.konkuk.bit.bitpay.table.dto.TableDto;
import com.konkuk.bit.bitpay.table.service.TableServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tables")
@RequiredArgsConstructor
public class TableController {

    private final TableServiceImpl tableService;


    @GetMapping("")
    public List<TableDto> getTableList() {
        return tableService.getTableList();
    }

    @PostMapping("/confirm_cleaned/{tableNumber}")
    public TableDto confirmCleaned(@PathVariable Integer tableNumber){
        return tableService.confirmCleaned(tableNumber);
    }

    @PostMapping("/confirm_clean/{tableNumber}")
    public TableDto confirmClean(@PathVariable Integer tableNumber){
        return tableService.confirmClean(tableNumber);
    }

    @PostMapping("/confirm_active/{tableNumber}")
    public TableDto confirmActive(@PathVariable Integer tableNumber){
        return tableService.confirmActive(tableNumber);
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