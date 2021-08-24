package com.yaans.vending.controller;

import com.yaans.vending.domain.Product;
import com.yaans.vending.domain.user.Customer;
import com.yaans.vending.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    // TODO customer 등록 & 조회 기능 추가
    @PostMapping(path = "customer")
    public void createCustomer(@RequestParam Customer customer) {


    }

    // TODO 금액 충전
    public void chargeBalance() {

    }
    // TODO 상품 선택 -> Post path = "/product"


    // TODO 금액 환불
}
