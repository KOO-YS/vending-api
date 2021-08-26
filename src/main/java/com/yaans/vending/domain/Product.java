package com.yaans.vending.domain;

import lombok.*;

import javax.persistence.*;

@Entity(name = "products")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name", nullable = false)
    private String name;
    @Column(name = "product_price")
    private Integer price;

}
