package com.yaans.vending.service;

import com.yaans.vending.domain.Product;
import com.yaans.vending.domain.VendingMachine;
import com.yaans.vending.domain.user.Customer;
import com.yaans.vending.error.LackOfBalanceException;
import com.yaans.vending.error.custom.EntityNotFoundException;
import com.yaans.vending.error.custom.InvalidNumberException;
import com.yaans.vending.error.custom.LackOfBudgetException;
import com.yaans.vending.repository.CustomerRepository;
import com.yaans.vending.repository.MachineRepository;
import com.yaans.vending.repository.StockRepository;
import com.yaans.vending.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final MachineService machineService;
    private final MachineRepository machineRepository;
    private final StockRepository stockRepository;
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public int refundChange(Long customerId, Long machineId) throws LackOfBudgetException, IllegalArgumentException, EntityNotFoundException {
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

    public Customer getCustomer(Long customerId) throws EntityNotFoundException{
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            throw new EntityNotFoundException("?????? Customer??? ???????????? ????????????");
        }
        return customer;
    }

    @Transactional
    public void setMachineBalance(Long machineId, Long customerId, Integer balance) throws LackOfBudgetException, EntityNotFoundException, IllegalArgumentException {
        // user
        chargeBalance(customerId, balance);
        // machine
        machineService.setBalance(machineId, balance);
    }

    public int chargeBalance(Long customerId, Integer balance) throws LackOfBudgetException, EntityNotFoundException, IllegalArgumentException{
        Customer customer = customerRepository.findById(customerId).orElse(null);      // NoSuchElementException
        if (customer == null)
            throw new EntityNotFoundException(customerId+"??? customer??? ?????? ??? ????????????.");

        int result = customer.getBudget() - balance;
        if (result < 0)
            throw new LackOfBudgetException("?????? ???????????? :: "+balance+" -> "+customerId+"??? customer??? ??????"+customer.getBudget()+"??? ???????????????");

        customer.setBudget(result);
        customerRepository.save(customer);

        return result;
    }

    public void setBudget(Long customerId, Integer budget) throws EntityNotFoundException, IllegalArgumentException {
        Customer customer = getCustomer(customerId);
        customer.setBudget(customer.getBudget()+budget);
        customerRepository.save(customer);
    }

    @Transactional
    public void pickProduct(Long customerId, Long stockId, Long machineId) throws EntityNotFoundException, InvalidNumberException {

        VendingMachine machine = machineService.getMachine(machineId);
        Product product = machineService.getStock(stockId).getProduct();

        int result = machine.getBalance() - product.getPrice();
        if (result<0)
            throw new LackOfBalanceException(machineId+"??? ??????????????? "+product+"??? ????????? ??????(??????: "+machine.getBalance()+ ")??? ????????????");

        // customer belong??? product ?????? ??????
        Customer customer = getCustomer(customerId);
        customer.addBelong(product);

        // stock?????? count ??????
        machineService.stockDown(stockId);

    }

}
