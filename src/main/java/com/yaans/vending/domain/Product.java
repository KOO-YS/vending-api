package com.yaans.vending.domain;

import lombok.*;

import javax.persistence.*;

@Entity(name = "product")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id", nullable = false)
    private Long id;

    @Column(name = "product_name", nullable = false)
    private String name;
    @Column(name = "product_price", columnDefinition = "5000")
    private Integer price;

}
