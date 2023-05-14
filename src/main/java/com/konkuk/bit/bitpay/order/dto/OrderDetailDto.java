package com.konkuk.bit.bitpay.order.dto;

import com.konkuk.bit.bitpay.order.domain.OrderDetail;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class OrderDetailDto {
    private Long menuId;
    private Integer quantity;

    public OrderDetailDto(OrderDetail source) {
        this.menuId = source.getMenuId();
        this.quantity = source.getQuantity();
    }
}
