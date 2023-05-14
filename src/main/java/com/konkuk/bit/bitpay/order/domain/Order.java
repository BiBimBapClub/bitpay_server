package com.konkuk.bit.bitpay.order.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Builder @Data
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
    private LocalDateTime timestamp;

    @Column(name = "order_status")
    private String status;

    @Column(name = "order_table_number")
    private Integer tableNumber;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> detailList;

    @Transient
    public final static String STATUS_BEFORE_PAYMENT = "ORDER_STATUS_BEFORE_PAYMENT";
    @Transient
    public final static String STATUS_PREPARING = "ORDER_STATUS_PREPARING";
    @Transient
    public final static String STATUS_COMPLETE = "ORDER_STATUS_COMPLETE";
    @Transient
    public final static String STATUS_ALL = "ORDER_STATUS_ALL";
}
