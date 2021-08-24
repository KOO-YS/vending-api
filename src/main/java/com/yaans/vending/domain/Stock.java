package com.yaans.vending.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity(name = "stock")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "stock_id")
    private Integer id;

    // JPA 연관관계 주인은 외래키를 관리하는 곳
    @ManyToOne
    @JoinColumn(name = "machine_id")
    private VendingMachine vendingMachine;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "stock_count")
    @ColumnDefault("0")
    private Integer count;


}
