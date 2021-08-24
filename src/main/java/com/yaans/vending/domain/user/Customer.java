package com.yaans.vending.domain.user;

import com.yaans.vending.domain.Product;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "customer")
@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class Customer extends User{

    @Column(name = "ct_budget")
    @ColumnDefault("5000")
    private int budget;

    @OneToMany
    @Column(name = "ct_belong")
    private List<Product> belong;
}
