package com.yaans.vending.error;

public class LackOfBudgetException extends RuntimeException {
    public LackOfBudgetException() {
        super("충전할 예산이 부족합니다");
    }
}
