package com.yaans.vending.error.custom;

import com.yaans.vending.error.ErrorCode;

public class EntityNotFoundException extends CustomException{
    public EntityNotFoundException(String message) {
        super(message);
    }
}
