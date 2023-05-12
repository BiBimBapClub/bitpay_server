package com.konkuk.bit.bitpay.table;

import jakarta.persistence.Column;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.sql.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@RedisHash(value = "table", timeToLive = -1L)
public class Table {
    @Id
    @Column(name = "table_number")
    private Integer number;
    @Column(name = "table_status")
    private String status;
    @Column(name = "table_description")
    private String description;
    @CreatedDate
    @Column(name = "table_updated_time")
    private Date updatedTime;
}
