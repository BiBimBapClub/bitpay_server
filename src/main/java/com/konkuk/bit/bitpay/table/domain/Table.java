package com.konkuk.bit.bitpay.table.domain;

import jakarta.persistence.Column;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@RedisHash(value = "table", timeToLive = -1L)
public class Table {
    @Id
    @Column(name = "table_number")
    private String number;

    @Column(name = "table_uuid")
    private String uuid;
    @Column(name = "table_status")
    private String status;
    @Column(name = "table_order_id")
    private List<Long> orders = new ArrayList<>();
    @CreatedDate
    @Column(name = "table_updated_time")
    private LocalDateTime updatedTime;
}
