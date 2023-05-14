package com.konkuk.bit.bitpay.tablehistory.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "table_history_id")
    private Long id;

    @Column(name = "table_number")
    private Integer tableNumber;

    @Column(name = "table_history_timestamp")
    private LocalDateTime timestamp;

    //order에 의한 type
    //table에 의한 type
    @Column(name = "table_history_type")
    private String type;

    //order에 의한 description: 주문한 내역, 총 주문 금액
    //table에 의한 description: table 상태 변경 내용, table 이용 시간 추가
    @Column(name = "table_history_description")
    private String description;

    @PrePersist
    public void timestamp() {
        this.timestamp = LocalDateTime.now();
    }
}
