package com.konkuk.bit.bitpay.menu.web.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MenuResponseDto {

    private Long menuNumber;
    private Integer price;
    private String name;
    private Integer remain;
    private String status;
}
