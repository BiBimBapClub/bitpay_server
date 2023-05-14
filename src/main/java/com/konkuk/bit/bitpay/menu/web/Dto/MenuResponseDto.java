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
    private Integer remain;
    private Boolean status;
}
