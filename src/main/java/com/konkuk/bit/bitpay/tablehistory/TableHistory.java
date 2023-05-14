package com.konkuk.bit.bitpay.tablehistory;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "table_history_id")
    private Long id;

    @Column(name = "table_number", unique = true)
    private Integer tableNumber;

    @Column(name = "table_history_timestamp")
    private Date timestamp;

    @Column(name = "table_history_type")
    private String type;

    @Column(name = "table_history_description")
    private String description;

    // TODO
    // 필요한거 추가
}
