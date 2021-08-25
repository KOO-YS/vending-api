package com.yaans.vending.controller;

import com.yaans.vending.domain.dto.newCustomer;
import com.yaans.vending.domain.user.Customer;
import com.yaans.vending.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping(path = "customer")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody newCustomer input) {
        Customer customer = Customer.builder()
                .name(input.getName())
                .budget(input.getBudget())
                .build();

        Customer save = customerService.createCustomer(customer);
        return ResponseEntity.ok().body(save);
    }

    @GetMapping(path = "customer/{customerId}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long customerId) {
        Customer result = customerService.getCustomer(customerId);
        return ResponseEntity.ok().body(result);
    }

    // TODO 금액 충전 -> Post path = "machine/balance"
    public ResponseEntity<Customer> chargeBalance() {

        return ResponseEntity.ok().body(null);
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
            return ResponseEntity.internalServerError().body("자판기가 존재하지 않습니다.");
        }
        return ResponseEntity.ok().body(change);
    }
}
