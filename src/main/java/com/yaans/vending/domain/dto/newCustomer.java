package com.yaans.vending.domain.dto;

import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * Customer 객체 생성에서 필요한 입력값
 */
@Getter
public class newCustomer {
    @NotBlank(message = "name can not be blanked")
    private String name;

    @Min(0) @Max(Integer.MAX_VALUE)
    private Integer budget;
}