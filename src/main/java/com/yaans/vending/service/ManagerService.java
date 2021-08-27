package com.yaans.vending.service;

import com.yaans.vending.domain.user.Manager;
import com.yaans.vending.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;

    public Manager createManager(Manager manager) {
        return managerRepository.save(manager);
    }

    public List<Manager> getManagerList() {
        return managerRepository.findAll();
    }
}
