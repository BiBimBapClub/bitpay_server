package com.konkuk.bit.bitpay.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "order_total_price")
    private Integer totalPrice;

    @Column(name = "order_timestamp")
    private Date timestamp;

    @Column(name = "order_status")
    private String status;

    @Column(name = "order_table_id")
    private Integer tableId;

    @OneToMany
    private List<OrderDetail> detailList;
}
