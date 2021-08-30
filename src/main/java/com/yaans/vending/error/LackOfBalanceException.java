package com.yaans.vending.error;

import com.yaans.vending.error.custom.CustomException;

public class LackOfBalanceException extends CustomException {
    public LackOfBalanceException(String s) {
        super(s);
    }
}
