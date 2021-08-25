package com.yaans.vending.service;

import com.yaans.vending.domain.Product;
import com.yaans.vending.domain.Stock;
import com.yaans.vending.domain.VendingMachine;
import com.yaans.vending.error.IllegalMachineStateException;
import com.yaans.vending.repository.MachineRepository;
import com.yaans.vending.repository.ProductRepository;
import com.yaans.vending.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MachineService {
    private final ProductRepository productRepository;
    private final MachineRepository machineRepository;
    private final StockRepository stockRepository;

    public List<Product> getProductList() {
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    public int refundBalance(Long machineId) throws NoSuchElementException {
        VendingMachine machine = machineRepository.findById(machineId).orElseThrow();   // NoSuchElementException
        int balance = machine.getBalance();

        if(balance < 0) throw new IllegalMachineStateException();

        machine.setBalance(0);
        machineRepository.save(machine);
        return balance;
    }

    public VendingMachine createMachine() {
        VendingMachine machine = VendingMachine.builder().build();
        return machineRepository.save(machine);
    }

    public VendingMachine getMachine(Long machineId) {
        VendingMachine machine = machineRepository.findById(machineId).orElse(null);
        return machine;
    }

    public boolean saveProduct(Product save) {
        Product result = productRepository.save(save);
        int success = productRepository.countById(result.getId());
        return (success>0);
    }

    public List<Stock> getStockList(Long machineId) {
        List<Stock> stockList = stockRepository.findByVendingMachine(getMachine(machineId));
        return stockList;
    }

    public Product getProduct(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        return product;
    }

    public void setStock(Long machineId, Long productId, Integer count) {
        Product product = getProduct(productId);
        Stock stock = Stock.builder().product(product).count(count)
                .vendingMachine(getMachine(machineId)).build();
        stockRepository.save(stock);
    }

    public void setBalance(long machineId ,int money) {
        VendingMachine machine = getMachine(machineId);
        machine.setBalance(money);
        machineRepository.save(machine);
    }
}
