package com.yaans.vending.error;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INVALID_INPUT_VALUE(400, "잘못된 입력 값 입니다."),
    INVALID_TYPE_VALUE(000, "")
    ;

    private final String message;
    private int status;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }


}
