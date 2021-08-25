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

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final MachineRepository machineRepository;
    private final StockRepository stockRepository;
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;

    public int refundChange(long machineId) {
        VendingMachine machine = machineRepository.getById(machineId);
        int change = machine.getBalance();
        machine.setBalance(0);
//        machineRepository.save(machine);
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
