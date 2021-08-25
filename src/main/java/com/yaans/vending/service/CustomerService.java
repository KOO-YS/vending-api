package com.yaans.vending.service;

import com.yaans.vending.domain.VendingMachine;
import com.yaans.vending.domain.user.Customer;
import com.yaans.vending.domain.user.User;
import com.yaans.vending.repository.CustomerRepository;
import com.yaans.vending.repository.MachineRepository;
import com.yaans.vending.repository.StockRepository;
import com.yaans.vending.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final MachineService machineService;
    private final MachineRepository machineRepository;
    private final StockRepository stockRepository;
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public int refundChange(Long customerId, Long machineId) {
        // get From Machine
        int change = machineService.refundBalance(machineId);

        // set to Customer
        Customer customer = getCustomer(customerId);
        customer.setBudget(customer.getBudget() + change);
        customerRepository.save(customer);
        return change;
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getCustomerList() {
        List<Customer> list = customerRepository.findAll();
        return list;
    }

    public Customer getCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        return customer;
    }
}
