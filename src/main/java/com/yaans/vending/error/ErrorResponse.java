package com.yaans.vending.error;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private String message;
    private int status;
//    private List
    private String code;

//    @Getter
//    @NoArgsConstructor(access = AccessLevel.PROTECTED)
//    public static class FieldError {
//        private String field;
//        private String value;
//        private String reason;
//    }
}
