package com.yaans.vending.domain.user;

import lombok.Getter;

import javax.persistence.*;

// FIXME : dtype
@Entity(name = "users")
@Getter
public abstract class User {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long id;


}
