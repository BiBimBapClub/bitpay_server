package com.konkuk.bit.bitpay.order.dto;

import com.konkuk.bit.bitpay.order.domain.OrderDetail;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class OrderDetailDto {
    private String menuName;
    private Long menuId;
    private Integer quantity;

    public OrderDetailDto(OrderDetail source) {
        // TODO menu 에서 가져오기
        this.menuName = "TODO:GET_MENU_NAME";
        this.menuId = 0L;
        this.quantity = source.getQuantity();
    }
}
