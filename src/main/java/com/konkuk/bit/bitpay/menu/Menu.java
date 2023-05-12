package com.konkuk.bit.bitpay.menu;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    @Id
    @Column(name = "menu_id")
    private Long id;

    @Column(name = "menu_price")
    private Integer price;
    @Column(name = "menu_remain")
    private Integer remain;
    @Column(name = "menu_status")
    private String status;
}
