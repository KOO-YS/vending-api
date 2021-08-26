package com.yaans.vending.service;

import com.yaans.vending.domain.VendingMachine;
import com.yaans.vending.domain.user.Customer;
import com.yaans.vending.domain.user.User;
import com.yaans.vending.error.LackOfBudgetException;
import com.yaans.vending.repository.CustomerRepository;
import com.yaans.vending.repository.MachineRepository;
import com.yaans.vending.repository.StockRepository;
import com.yaans.vending.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
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

    public Customer createCustomer(Customer customer) throws IllegalArgumentException{
        return customerRepository.save(customer);
    }

    public List<Customer> getCustomerList() {
        List<Customer> list = customerRepository.findAll();
        return list;
    }

    public Customer getCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            throw new EntityNotFoundException();
        }
        return customer;
    }

    @Transactional
    public void setMachineBalance(Long machineId, Long customerId, Integer balance) {
        // user
        chargeBalance(customerId, balance);
        // machine
        machineService.setBalance(machineId, balance);
    }
    public int chargeBalance(Long customerId, Integer balance) {
        Customer customer = customerRepository.findById(customerId).orElseThrow();      // NoSuchElementException

        int result = customer.getBudget() - balance;
        System.out.println(result+"원 남음");
        if (result < 0) throw new LackOfBudgetException();

        customer.setBudget(result);
        customerRepository.save(customer);

        return result;
    }
}
