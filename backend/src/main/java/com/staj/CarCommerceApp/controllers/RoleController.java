package com.staj.CarCommerceApp.controllers;

import com.staj.CarCommerceApp.services.RoleService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostConstruct
    public void createRolesTable(){
        if (roleService.createRolesTable()) {
            System.out.println("Roles table successfully created!");
            return;
        }
        System.out.println("Roles table could not be created!");
        return;
    }
//    @DeleteMapping
//    public ResponseEntity<?> deleteRole(){
//        return ResponseEntity.ok().build();
//    }
//
//    @PatchMapping
//    public ResponseEntity<?> updateRole(@RequestBody Role role){
//        return ResponseEntity.ok().build();
//    }
}
