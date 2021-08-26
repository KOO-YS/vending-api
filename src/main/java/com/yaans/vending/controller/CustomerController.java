package com.yaans.vending.controller;

import com.yaans.vending.domain.dto.BalanceSet;
import com.yaans.vending.domain.dto.newCustomer;
import com.yaans.vending.domain.user.Customer;
import com.yaans.vending.error.IllegalMachineStateException;
import com.yaans.vending.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping(path = "customer")
    public ResponseEntity createCustomer(@Valid @RequestBody newCustomer input) {
        Customer customer = Customer.builder()
                .name(input.getName())
                .budget(input.getBudget())
                .build();

        Customer save;
        try {
            save = customerService.createCustomer(customer);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().body("customer를 생성할 수 없습니다");
        }
        return ResponseEntity.ok().body(save);
    }

    @GetMapping(path = "customer/{customerId}")
    public ResponseEntity getCustomer(@PathVariable Long customerId) {
        Customer result;
        try {
            result = customerService.getCustomer(customerId);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body("관련 customer가 존재하지 않습니다");
        }
        return ResponseEntity.ok().body(result);
    }

    // 금액 충전
    @PostMapping(path = "customer/charge")
    public ResponseEntity chargeBalance(@Valid @RequestBody BalanceSet set) {
        Long machineId = set.getMachineId();
        Long customerId = set.getCustomerId();
        Integer balance = set.getBalance();

        try {
            customerService.setMachineBalance(machineId, customerId, balance);
        } catch (Exception e) {
            System.out.println("FAIL");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("충전 실패");
        }
        System.out.println("SUCCESS");
        return ResponseEntity.status(HttpStatus.OK).body("충전 성공");
    }

    // TODO 상품 선택 -> Post path = "/product"


    // 금액 환불
    @GetMapping(path = "customer/{customerId}/refund/{machineId}")
    public ResponseEntity refundChange(@PathVariable Long customerId,
                                      @PathVariable Long machineId) {
        int change = 0;
        try {
            change = customerService.refundChange(customerId, machineId);

        } catch (NoSuchElementException e) {
            return ResponseEntity.internalServerError().body("해당 자판기가 존재하지 않습니다.");
        } catch (IllegalMachineStateException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.ok().body(change);
    }
}
