package com.konkuk.bit.bitpay.tablehistory.dto;

import java.util.UUID;
import lombok.Data;

@Data
public class TableHistoryCreateDto {

    private Long tableNumber;
    private String type;
    private String description;
    private UUID uuid;
}
