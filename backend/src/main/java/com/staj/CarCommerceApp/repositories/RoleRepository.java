package com.staj.CarCommerceApp.repositories;

import com.staj.CarCommerceApp.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByRoleName(String roleName);
    List <Role> findAllByRoleName(String roleName);
}
