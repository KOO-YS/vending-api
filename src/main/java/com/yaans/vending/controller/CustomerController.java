package com.yaans.vending.controller;

import com.yaans.vending.domain.dto.BalanceSet;
import com.yaans.vending.domain.dto.BudgetSet;
import com.yaans.vending.domain.dto.BuySet;
import com.yaans.vending.domain.dto.newCustomer;
import com.yaans.vending.domain.user.Customer;
import com.yaans.vending.error.custom.EntityNotFoundException;
import com.yaans.vending.error.custom.IllegalMachineStateException;
import com.yaans.vending.error.custom.LackOfBudgetException;
import com.yaans.vending.service.CustomerService;
import com.yaans.vending.service.MachineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;
    private final MachineService machineService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * customer 등록
     */
    @PostMapping(path = "customer")
    public ResponseEntity createCustomer(@Valid @RequestBody newCustomer input) {
        Customer customer = Customer.builder()
                .name(input.getName())
                .budget(input.getBudget())
                .build();

        log.info("INFO :: create customer : {}", customer);
        Customer save = customerService.createCustomer(customer);

        return ResponseEntity.ok().body(save);
    }

    /**
     * customer 정보 가져오기
     */
    @GetMapping(path = "customer/{customerId}")
    public ResponseEntity getCustomer(@PathVariable Long customerId) {

        log.info("INFO :: get customer by id : {}", customerId);
        Customer result = customerService.getCustomer(customerId);

        return ResponseEntity.ok().body(result);
    }

    /**
     * 자판기 금액 충전 
     */
    @PostMapping(path = "customer/charge")
    public ResponseEntity chargeBalance(@Valid @RequestBody BalanceSet set) {
        Long machineId = set.getMachineId();
        Long customerId = set.getCustomerId();
        Integer balance = set.getBalance();
        log.info("INFO :: customer {} charges balance {} at machine {}", customerId, balance, machineId);

        customerService.setMachineBalance(machineId, customerId, balance);

        return ResponseEntity.status(HttpStatus.OK).body("자판기 충전 성공");
    }

    /**
     * 사용자 예산 충전
     */
    @PostMapping(path = "customer/budget")
    public ResponseEntity chargeBudget(@Valid @RequestBody BudgetSet set) {
        Long customerId = set.getCustomerId();
        Integer budget = set.getBudget();
        log.info("INFO :: customer {} charges budget {} by self", customerId, budget);

        customerService.setBudget(customerId, budget);
        return ResponseEntity.status(HttpStatus.OK).body("예산 충전 성공");
    }

    /**
     * customer 상품 구매
    */
    @PostMapping(path = "customer/product")
    public ResponseEntity buyProduct(@RequestBody BuySet set) {

        Long customerId = set.getCustomerId();
        Long stockId = set.getStockId();
        Long machineId = set.getMachineId();

        log.info("INFO :: {} customer buy a product in stock {} & machine {}", customerId, stockId, machineId);
        customerService.pickProduct(customerId, stockId, machineId);

        return ResponseEntity.ok("상품 구매 성공");
    }

    // 금액 환불
    @GetMapping(path = "customer/{customerId}/refund/{machineId}")
    public ResponseEntity refundChange(@PathVariable Long customerId,
                                      @PathVariable Long machineId) {
        log.info("INFO :: customer {} refund change about machine {}", customerId, machineId);

        int change = customerService.refundChange(customerId, machineId);
        return ResponseEntity.ok().body(change);
    }
}
