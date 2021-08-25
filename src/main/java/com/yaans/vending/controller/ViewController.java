package com.yaans.vending.controller;

import com.yaans.vending.domain.Stock;
import com.yaans.vending.domain.VendingMachine;
import com.yaans.vending.domain.user.Customer;
import com.yaans.vending.service.CustomerService;
import com.yaans.vending.service.MachineService;
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
    private final MachineService machineService;

    @GetMapping(path = "")
    public String index(Model model) {
        List<Customer> customerList = customerService.getCustomerList();
        model.addAttribute("customerList", customerList);
        return "index";
    }

    @GetMapping(path = "register")
    public String register() {
        return "register";
    }

    @GetMapping(path = "vending-machine/{machineId}")
    public String vendingMachine(Model model, @PathVariable Long machineId) {
        VendingMachine machine = machineService.getMachine(machineId);
        List<Stock> stockList = machineService.getStockList(machineId);
        model.addAttribute("machine", machine);
        model.addAttribute("stockList", stockList);

        return "machine";
    }
}
