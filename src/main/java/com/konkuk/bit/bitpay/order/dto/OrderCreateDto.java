package com.konkuk.bit.bitpay.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter @NoArgsConstructor
public class OrderCreateDto {
    private Map<Long,Integer> orderDetail;
    private Integer tableNumber;
}
