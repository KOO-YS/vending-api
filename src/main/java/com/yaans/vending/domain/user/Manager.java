package com.yaans.vending.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity(name = "manager")
@DiscriminatorValue("manager")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Manager extends User{

    @Column(name = "manager_level")
    @Enumerated(EnumType.STRING)
    AccessLevel accessLevel;
}
