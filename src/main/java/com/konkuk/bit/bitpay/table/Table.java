package com.konkuk.bit.bitpay.table;

import jakarta.persistence.Column;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;
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
    private Integer number;

    @Column(name = "table_uuid")
    private UUID uuid;
    @Column(name = "table_status")
    private String status;
    @CreatedDate
    @Column(name = "table_updated_time")
    private LocalDateTime updatedTime;
}
