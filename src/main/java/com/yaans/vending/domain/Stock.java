package com.yaans.vending.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity(name = "stocks")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "stock_id")
    private Long id;

    // JPA 연관관계 주인은 외래키를 관리하는 곳
    @ManyToOne
    @JoinColumn(name = "machine_id", nullable = false)
    private VendingMachine vendingMachine;

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "stock_count", nullable = false)
    @ColumnDefault("0")
    private Integer count;

    public void setCount(int c) {
        this.count = c;
    }

}
