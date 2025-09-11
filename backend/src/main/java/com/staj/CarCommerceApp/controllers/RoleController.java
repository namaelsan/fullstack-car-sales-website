package com.staj.CarCommerceApp.controllers;

import com.staj.CarCommerceApp.entities.Role;
import com.staj.CarCommerceApp.services.LoggingService;
import com.staj.CarCommerceApp.services.RoleService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;
    private final LoggingService loggingService;


    @PostConstruct
    public void createRolesTable(){
        if (roleService.createRolesTable()) {
            loggingService.logInfo("Roles table successfully created!");
            return;
        }
        loggingService.logInfo("Roles table could not be created!");
        return;
    }
    @DeleteMapping
    public ResponseEntity<?> deleteRole(){
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<?> updateRole(@RequestBody Role role){
        return ResponseEntity.ok().build();
    }
}
