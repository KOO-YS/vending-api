package com.yaans.vending.domain.user;

import com.yaans.vending.domain.Product;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@DiscriminatorValue("customer")
@Entity(name = "customer")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer extends User{

    @Column(name = "ct_budget")
    @ColumnDefault("0")
    private int budget;

    @OneToMany
    @Column(name = "ct_belong")
    private List<Product> belong = new ArrayList<>();

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public void addBelong(Product product) {
        belong.add(product);
    }

    public void removeBelong(Product product) {
        belong.remove(product);
    }

}
