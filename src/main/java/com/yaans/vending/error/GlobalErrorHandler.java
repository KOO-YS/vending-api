package com.yaans.vending.error;

import com.yaans.vending.error.custom.CustomException;
import com.yaans.vending.error.custom.EntityNotFoundException;
import com.yaans.vending.error.custom.LackOfBudgetException;
import com.yaans.vending.error.custom.SaveFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class GlobalErrorHandler {

    /**
     * 적절하지 않은 입력값에 대한 예외
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleNoValidException(MethodArgumentNotValidException e) {
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
        log.error(e.toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * 내부 서버 (JPA 관련) 에러
     */
    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
        log.error(e.toString());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 아이디 값에 적합한 객체가 존재하지 않는다
     */
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException e) {
        final ErrorResponse response = ErrorResponse.of(ErrorCode.ENTITY_NOT_FOUND);
        log.error(e.toString());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 데이터 저장 실패
     */
    @ExceptionHandler(SaveFailedException.class)
    protected ResponseEntity<ErrorResponse> handleSaveFailException(SaveFailedException e) {
        final ErrorResponse response = ErrorResponse.of(ErrorCode.SAVE_FAIL_ERROR);
        log.error(e.toString());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Customer의 예산 부족 예외
     */
    @ExceptionHandler(LackOfBudgetException.class)
    protected ResponseEntity<ErrorResponse> handleLackOfBudgetException(LackOfBudgetException e) {
        final ErrorResponse response = ErrorResponse.of(ErrorCode.LACK_OF_BUDGET_ERROR);
        log.error(e.toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * 자판기의 충전 예산 부족 예외
     */
    @ExceptionHandler(LackOfBalanceException.class)
    protected ResponseEntity<ErrorResponse> handleLackOfBalanceException(LackOfBalanceException e) {
        final ErrorResponse response = ErrorResponse.of(ErrorCode.LACK_OF_BALANCE_ERROR);
        log.error(e.toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * 잡히지 않은 비즈니스 예외
     */
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleTheOtherException(CustomException e) {
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_LOGIC_EXCEPTION);
        log.error(e.toString());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 잡히지 않은 전체 예외
     */
    @ExceptionHandler(Throwable.class)
    protected ResponseEntity handleException(Throwable t) {
        log.error(t.toString());
        return new ResponseEntity<>(t.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
