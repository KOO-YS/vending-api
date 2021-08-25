package com.yaans.vending.repository;

import com.yaans.vending.domain.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
