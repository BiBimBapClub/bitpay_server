package com.konkuk.bit.bitpay.tablehistory.dto;

import lombok.Data;

@Data
public class TableHistoryCreateDto {

    private Long tableNumber;
    private String type;
    private String description;
}
