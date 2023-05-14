package com.konkuk.bit.bitpay.table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TableDto {
    private Integer number;
    private UUID uuid;
    private String status;
    private String description;
    private Date updatedTime;
}