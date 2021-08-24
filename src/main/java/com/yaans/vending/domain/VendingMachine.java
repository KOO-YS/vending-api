package com.yaans.vending.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "vending_machine")
@Builder
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class VendingMachine {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "machine_id")
    private Long id;

    @Column(name = "balance")
    private Integer balance;

    public void setBalance(int balance) {
        this.balance += balance;
    }
}
