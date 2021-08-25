package com.yaans.vending.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

// https://jyami.tistory.com/23
@DiscriminatorColumn(name = "DTYPE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)       // 싱글 테이블 전략
@Entity(name = "users")
@SuperBuilder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class User {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long id;

    @Column(name = "user_name")
    private String name;

}
