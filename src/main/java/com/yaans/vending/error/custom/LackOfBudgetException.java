package com.yaans.vending.error.custom;

public class LackOfBudgetException extends RuntimeException {
    public LackOfBudgetException(String message) {
        super(message);
    }
}
