package com.yaans.vending.error;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INVALID_INPUT_VALUE(400, "잘못된 입력 값 입니다."),
    INTERNAL_SERVER_ERROR(500, "내부 서버 오류가 발생했습니다."),
    INVALID_TYPE_VALUE(400, "잘못된 타입 값입니다."),
    ENTITY_NOT_FOUND(400, "해당 엔티티를 찾을 수 없습니다."),
    LACK_OF_BUDGET_ERROR(400, "customer의 예산이 필요보다 부족합니다."),
    LACK_OF_BALANCE_ERROR(400, "자판기의 충전액이 필요보다 부족합니다"),

    SAVE_FAIL_ERROR(500, "내부 서버 오류로 인해 저장을 할 수 없습니다."),
    INVALID_LOGIC_EXCEPTION(500, "올바른 예외 처리가 되지 않았습니다."),
    ;

    private final String message;
    private int status;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }


}
