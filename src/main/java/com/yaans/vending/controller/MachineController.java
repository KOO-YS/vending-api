package com.yaans.vending.controller;

import com.yaans.vending.domain.Product;
import com.yaans.vending.domain.Stock;
import com.yaans.vending.domain.VendingMachine;
import com.yaans.vending.error.custom.IllegalMachineStateException;
import com.yaans.vending.error.custom.InvalidNumberException;
import com.yaans.vending.service.MachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class MachineController {
    private final MachineService machineService;

    // 자판기 기계 등록
    @GetMapping(path = "machine/new")
    public ResponseEntity createMachine() {
        VendingMachine machine = null;
        try {
            machine = machineService.createMachine();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("자판기를 생성할 수 없습니다");
        }
        return ResponseEntity.ok().body(machine);
    }
    // 지금은 필요없지만 추후에 기계 관련 필드 추가되면!
    @GetMapping(path = "machine/{machineId}")
    public ResponseEntity<VendingMachine> getMachine(@PathVariable Long machineId) {
        VendingMachine machine = machineService.getMachine(machineId);
        return ResponseEntity.ok().body(machine);
    }
    // 자판기 금액 반환
    @GetMapping(path = "machine/{machineId}/balance")
    public ResponseEntity getBalance(@PathVariable long machineId) {
        int balance;
        try {
            balance = machineService.refundBalance(machineId);
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body("해당 자판기가 존재하지 않습니다.");
        } catch (IllegalMachineStateException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.ok(balance);
    }
    // 자판기 금액 충전
    @PostMapping(path = "machine/{machineId}/balance")
    public ResponseEntity<String> setBalance(@PathVariable long machineId,
//                                            @RequestBody Map<String, Integer> map) {
//                                            !! QUESTION about https://stackoverflow.com/questions/49192783/get-integer-on-requestbody
                                             @RequestBody Integer balance) {
//        int balance = map.get("balance");
        try {
            machineService.setBalance(machineId, balance);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body("해당 자판기가 존재하지 않습니다");
        } catch (InvalidNumberException ne) {
            return ResponseEntity.badRequest().body(ne.getMessage());
        }
        return ResponseEntity.ok().body("금액 충전 완료");
    }
    
    // 자판기에 재고 등록
    @PostMapping(path = "machine/{machineId}/stock")
    public ResponseEntity<String> setStock(@PathVariable Long machineId,
                                           @RequestBody Map<String, String> map) {
        Long productId = Long.parseLong(map.get("productId"));
        int count = Integer.parseInt(map.get("count"));
        machineService.setStock(machineId, productId, count);

        return ResponseEntity.ok("재고 등록 완료");
    }

    // 자판기 내부 재고 리스트 확인
    @GetMapping(path = "machine/{machineId}/stocks")
    public ResponseEntity<List<Stock>> getStockList(@PathVariable Long machineId) {
        List<Stock> stocks = machineService.getStockList(machineId);
        return ResponseEntity.ok().body(stocks);
    }

    // 총 상품 리스트 확인
    @GetMapping(path = "products")
    public ResponseEntity<List<Product>> getProductList() {
        List<Product> productList = machineService.getProductList();
        return ResponseEntity.ok().body(productList);
    }

    // 상품 저장
    @PostMapping(path = "product")
    public ResponseEntity saveProduct(@RequestBody Product product) {
        Product save = Product.builder().name(product.getName()).price(product.getPrice()).build();
        String result;
        if(!machineService.saveProduct(save))
            return ResponseEntity.internalServerError().body("상품 저장에 오류가 발생했습니다.");

        return ResponseEntity.ok(save);
    }

    // 상품 확인
    @GetMapping(path = "product/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable long productId){
        Product product = machineService.getProduct(productId);
        return ResponseEntity.ok().body(product);
    }

}
