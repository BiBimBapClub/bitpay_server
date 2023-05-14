package com.konkuk.bit.bitpay.table;

import com.konkuk.bit.bitpay.order.dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TableDto {
    private String number;
    private UUID uuid;
    private String status;
    private String description;
    private LocalDateTime updatedTime;

    private List<OrderDto> orders;
}