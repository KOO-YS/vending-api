package com.yaans.vending.error.custom;

public class SaveFailedException extends CustomException{
    public SaveFailedException(String message) {
        super(message);
    }
}
