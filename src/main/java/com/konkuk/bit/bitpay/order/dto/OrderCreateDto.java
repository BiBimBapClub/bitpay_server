package com.konkuk.bit.bitpay.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter @Setter
@NoArgsConstructor
public class OrderCreateDto {
    /*
        menuNumber, menuQuantity
     */
    private Map<Integer,Integer> orderDetail;
    private Integer tableNumber;
}
