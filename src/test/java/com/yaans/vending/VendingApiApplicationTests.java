package com.yaans.vending;

import com.yaans.vending.domain.Product;
import com.yaans.vending.domain.Stock;
import com.yaans.vending.domain.user.Customer;
import com.yaans.vending.domain.user.Manager;
import com.yaans.vending.repository.ProductRepository;
import com.yaans.vending.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VendingApiApplicationTests {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void getEntity() {
//        Product product = Product.builder().name("빼빼로").price(1500).build();
//
//        Stock stock = Stock.builder().product(product).count(10).build();
//
//        productRepository.save(product);

        Customer customer = Customer.builder().budget(1000000).build();
        Manager manager = Manager.builder().build();
        userRepository.save(customer);
        userRepository.save(manager);
    }
}
