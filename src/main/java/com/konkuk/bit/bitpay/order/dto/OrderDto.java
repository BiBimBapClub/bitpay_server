package com.konkuk.bit.bitpay.order.dto;

import com.konkuk.bit.bitpay.order.domain.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter @NoArgsConstructor
public class OrderDto {
    private Long id;
    private Integer totalPrice;
    private LocalDateTime timestamp;
    private String status;
    private Integer tableNumber;

    private List<OrderDetailDto> detailList;

    public OrderDto(Order source) {
        this.id = source.getId();
        this.totalPrice = source.getTotalPrice();
        this.timestamp = source.getTimestamp();
        this.status = source.getStatus();
        this.tableNumber = source.getTableNumber();
        this.detailList = source.getDetailList().stream()
                .map(detail -> new OrderDetailDto(detail))
                .collect(Collectors.toList());
    }
}
