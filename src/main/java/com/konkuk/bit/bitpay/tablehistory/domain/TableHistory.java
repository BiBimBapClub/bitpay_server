package com.konkuk.bit.bitpay.tablehistory.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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

    @Column(name = "table_number", unique = true)
    private Integer tableNumber;

    @Column(name = "table_history_timestamp")
    private Date timestamp;

    @Column(name = "table_history_type")
    private String type;

    @Column(name = "table_history_description")
    private String description;
}
