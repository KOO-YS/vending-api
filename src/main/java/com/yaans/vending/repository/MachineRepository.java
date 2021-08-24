package com.yaans.vending.repository;


import com.yaans.vending.domain.VendingMachine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MachineRepository extends JpaRepository<VendingMachine, Long> {

}
