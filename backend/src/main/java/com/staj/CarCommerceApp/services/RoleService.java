package com.staj.CarCommerceApp.services;

import com.staj.CarCommerceApp.models.Role;
import com.staj.CarCommerceApp.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role getRolesByUserId(Long userId) {
        roleRepository.findAllById()
    }
}
