package com.yaans.vending.domain.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class BuySet {
    @NotNull(message = "customer를 찾을 수 없습니다.")
    private Long customerId;
    
    @NotNull(message = "stock을 찾을 수 없습니다.")
    private Long stockId;
    
    @NotNull(message = "machine을 선택해주세요")
    private Long machineId;
}
