package com.yaans.vending.domain.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class BalanceSet {
    @NotNull
    private Long customerId;
    @NotNull
    private Long machineId;
    @NotNull
    private Integer balance;
}
