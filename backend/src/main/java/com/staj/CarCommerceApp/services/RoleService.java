package com.staj.CarCommerceApp.services;

import com.staj.CarCommerceApp.entities.Role;
import com.staj.CarCommerceApp.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public boolean createRolesTable() {
        if (roleRepository.count() != 0) {
            return false;
        }

        Role role1 = new Role();
        role1.setRoleName("USER");

        Role role2 = new Role();
        role2.setRoleName("ADMIN");

        role1 = roleRepository.save(role1);
        role2 = roleRepository.save(role2);

        if (role1 == null || role2 == null) {
            return false;
        }
        return true;
    }
}
