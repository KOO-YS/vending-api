package com.yaans.vending.error.custom;

public class IllegalMachineStateException extends RuntimeException {
    public IllegalMachineStateException() {
        super("자판기 상태 이상");
    }
}
