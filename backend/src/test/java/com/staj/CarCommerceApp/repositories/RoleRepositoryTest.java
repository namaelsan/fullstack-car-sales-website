package com.staj.CarCommerceApp.repositories;

import com.staj.CarCommerceApp.entities.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void findByRoleName_ShouldReturnRole_WhenFound() {
        Role role = new Role();
        role.setRoleName("ADMIN");
        roleRepository.save(role);

        var newRole = roleRepository.findByRoleName("ADMIN");

        assertEquals(role.toString(), newRole.toString());
    }

    @Test
    void findByRoleName_ShouldReturnNull_WhenNotFound() {
        var role = roleRepository.findByRoleName("admin");

        assertNull(role);
    }


}