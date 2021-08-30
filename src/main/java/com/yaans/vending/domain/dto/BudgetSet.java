package com.yaans.vending.domain.dto;

import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
public class BudgetSet {
    @NotNull(message = "사용자를 찾을 수 없습니다")
    private Long customerId;
    
    @Min(value = 0, message = "충전 금액이 올바르지 않습니다")
    @Max(value = 50000, message = "최대 50,000원까지 충전 가능합니다")
    @NotNull
    private Integer budget;
}
