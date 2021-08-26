package com.yaans.vending.error;

public class InvalidNumberException extends RuntimeException {
    public InvalidNumberException() {
        super("0 이상의 숫자를 입력해주세요");
    }
}
