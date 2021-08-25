package com.yaans.vending.controller;

import com.yaans.vending.domain.Product;
import com.yaans.vending.domain.user.Customer;
import com.yaans.vending.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping(path = "customer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        customer = Customer.builder().name(customer.getName()).budget(customer.getBudget()).build();
        Customer save = customerService.createCustomer(customer);
        return ResponseEntity.ok().body(save);
    }

    @GetMapping(path = "customer/{customerId}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long customerId) {
        System.out.println(customerId);
        Customer result = customerService.getCustomer(customerId);
        return ResponseEntity.ok().body(result);
    }

    // TODO 금액 충전 -> Post path = "machine/balance"
    public ResponseEntity<Customer> chargeBalance() {

        return ResponseEntity.ok().body(null);
    }

    // TODO 상품 선택 -> Post path = "/product"


    // 금액 환불
    @GetMapping(path = "customer/refund/{machineId}")
    public ResponseEntity<Integer> refundChange(@PathVariable long machineId) {
        int change = customerService.refundChange(machineId);
        return ResponseEntity.ok().body(change);
    }
}
