package com.yaans.vending.controller;

import com.yaans.vending.domain.Stock;
import com.yaans.vending.domain.VendingMachine;
import com.yaans.vending.domain.user.Customer;
import com.yaans.vending.domain.user.Manager;
import com.yaans.vending.service.CustomerService;
import com.yaans.vending.service.MachineService;
import com.yaans.vending.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ViewController {

    private final CustomerService customerService;
    private final ManagerService managerService;
    private final MachineService machineService;

    @GetMapping(path = "")
    public String index(Model model) {
        List<Customer> customerList = customerService.getCustomerList();
        List<Manager> managerList = managerService.getManagerList();

        model.addAttribute("customerList", customerList);
        model.addAttribute("managerList", managerList);

        return "index";
    }

    @GetMapping(path = "register")
    public String register() {
        return "register";
    }

    @GetMapping(path = "customer/{customerId}/machines")
    public String machineListByC(@PathVariable Long customerId,
                              Model model) {
        List<VendingMachine> machineList = machineService.getMachineList();

        model.addAttribute("customerId", customerId);
        model.addAttribute("machineList", machineList);
        return "machine-list";
    }

    @GetMapping(path = "manager/{managerId}/machines")
    public String machineListByM(@PathVariable Long managerId,
                                Model model) {
        List<VendingMachine> machineList = machineService.getMachineList();

        model.addAttribute("managerId", managerId);
        model.addAttribute("machineList", machineList);
        return "machine-list";
    }

    @GetMapping(path = "manager/machine/new")
    public String newMachine() {

        return "new-machine";
    }

    @GetMapping(path = "customer/{customerId}/machines/{machineId}")
    public String vendingMachine(@PathVariable Long machineId,
                                 @PathVariable Long customerId,
                                 Model model) {
        // machine
        VendingMachine machine = machineService.getMachine(machineId);
        List<Stock> stockList = machineService.getStockList(machineId);
        // customer
        Customer customer = customerService.getCustomer(customerId);

        model.addAttribute("machine", machine);
        model.addAttribute("stockList", stockList);
        model.addAttribute("customer", customer);

        return "machine";
    }
}
