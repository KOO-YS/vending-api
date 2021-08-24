package com.yaans.vending.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.Entity;

@Entity(name = "manager")
@Builder
@AllArgsConstructor
public class Manager extends User{


}
