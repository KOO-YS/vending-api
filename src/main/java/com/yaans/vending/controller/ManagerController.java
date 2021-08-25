package com.yaans.vending.controller;

import com.yaans.vending.domain.user.AccessLevel;
import com.yaans.vending.domain.user.Manager;
import com.yaans.vending.domain.user.User;
import com.yaans.vending.service.MachineService;
import com.yaans.vending.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ManagerController {
    private final MachineService machineService;
    private final ManagerService managerService;

    // 매니저 등록 // FIXME 매니저 권한 설정
    @PostMapping(path = "manager")
    public ResponseEntity<Manager> createManager(@RequestBody Manager manager) {
        manager = Manager.builder()
                .name(manager.getName())
                .accessLevel(AccessLevel.SUPER).build();
        Manager result = managerService.createManager(manager);
        return ResponseEntity.ok().body(result);
    }

}
