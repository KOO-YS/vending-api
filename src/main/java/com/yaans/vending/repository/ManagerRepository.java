package com.yaans.vending.repository;

import com.yaans.vending.domain.user.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
