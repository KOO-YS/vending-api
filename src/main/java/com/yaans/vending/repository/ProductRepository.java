package com.yaans.vending.repository;

import com.yaans.vending.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {


    int countById(long id);
}
