package com.yaans.vending.domain.dto;

import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Customer 객체 생성에서 필요한 입력값
 */
@Getter
public class newCustomer {
    @NotBlank(message = "이름을 작성해주세요")
    private String name;

    @Min(value = 0, message = "올바르지 않은 예산 범위입니다 (음수)")
    @Max(value = Integer.MAX_VALUE, message = "올바르지 않은 예산 범위입니다 (범위 초과)")
    @NotNull(message = "예산을 작성해주세요")
    private Integer budget;
}