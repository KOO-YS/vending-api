package com.yaans.vending.repository;

import com.yaans.vending.domain.Stock;
import com.yaans.vending.domain.VendingMachine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findByVendingMachine(VendingMachine vendingMachine);
}
