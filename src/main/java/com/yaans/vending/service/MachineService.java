package com.yaans.vending.service;

import com.yaans.vending.domain.Product;
import com.yaans.vending.domain.Stock;
import com.yaans.vending.domain.VendingMachine;
import com.yaans.vending.error.LackOfBalanceException;
import com.yaans.vending.error.custom.EntityNotFoundException;
import com.yaans.vending.error.custom.InvalidNumberException;
import com.yaans.vending.error.custom.LackOfBudgetException;
import com.yaans.vending.repository.MachineRepository;
import com.yaans.vending.repository.ProductRepository;
import com.yaans.vending.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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

    public int refundBalance(Long machineId) throws LackOfBudgetException, IllegalArgumentException {
        VendingMachine machine = getMachine(machineId);
        int balance = machine.getBalance();

        if(balance < 0)
            throw new LackOfBalanceException("환불할 충전 금액이 없습니다 -> machineId = "+machineId);

        machine.setBalance(0);
        machineRepository.save(machine);

        return balance;
    }

    public VendingMachine createMachine() throws IllegalArgumentException{
        VendingMachine machine = VendingMachine.builder().balance(0).build();
        return machineRepository.save(machine);
    }

    public VendingMachine getMachine(Long machineId) throws EntityNotFoundException{
        VendingMachine machine = machineRepository.findById(machineId).orElse(null);
        if (machine == null) {
            throw new EntityNotFoundException("해당 Machine이 존재하지 않습니다");
        }
        return machine;
    }

    public boolean saveProduct(Product save) {
        Product result = productRepository.save(save);
        int success = productRepository.countById(result.getId());
        return (success>0);
    }

    public List<Stock> getStockList(Long machineId) throws EntityNotFoundException{
        List<Stock> stockList = stockRepository.findByVendingMachine(getMachine(machineId));
        return stockList;
    }

    // stock 재고 정보 확인
    public Stock getStock(Long stockId) throws EntityNotFoundException{
        Stock stock = stockRepository.findById(stockId).orElse(null);
        if (stock == null) 
            throw new EntityNotFoundException(stockId+"번 stock을 찾을 수 없습니다");
        return stock;
    }

    public Product getProduct(Long productId) throws EntityNotFoundException{
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) 
            throw new EntityNotFoundException(productId+"번 product를 찾을 수 없습니다");
        return product;
    }

    public void setStock(Long machineId, Long productId, Integer count) throws EntityNotFoundException{
        Product product = getProduct(productId);
        Stock stock = Stock.builder().product(product).count(count)
                .vendingMachine(getMachine(machineId)).build();
        stockRepository.save(stock);
    }

    public void setBalance(long machineId ,int money) throws IllegalArgumentException, EntityNotFoundException{
        VendingMachine machine = getMachine(machineId);

        machine.setBalance(machine.getBalance() + money);
        machineRepository.save(machine);
    }

    public List<VendingMachine> getMachineList() {
        List<VendingMachine> machineList = machineRepository.findAll();
        return machineList;
    }

    public void stockDown(Long stockId) throws EntityNotFoundException{
        Stock stock = stockRepository.findById(stockId).orElse(null);
        if (stock == null)
            throw new EntityNotFoundException("stock을 찾을 수 없습니다. stockId = "+stockId);

        if (stock.getCount() < 1)
            throw new InvalidNumberException(stockId+"번 stock의 수량이 부족합니다");

        stock.setCount(stock.getCount()-1);
        stockRepository.save(stock);
    }
}
