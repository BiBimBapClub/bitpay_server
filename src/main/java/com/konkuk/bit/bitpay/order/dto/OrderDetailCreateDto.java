package com.konkuk.bit.bitpay.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class OrderDetailCreateDto {
    private Long menu_id;
    private Integer count;
}
