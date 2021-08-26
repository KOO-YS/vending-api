package com.yaans.vending.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity(name = "manager")
@DiscriminatorValue("manager")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Manager extends User{

    @Enumerated(EnumType.STRING)
    @Column(name = "manager_level")
//    @ColumnDefault("AccessLevel.SUPER")
    AccessLevel accessLevel;
}
