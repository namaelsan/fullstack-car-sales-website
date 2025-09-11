package com.staj.CarCommerceApp.repositories;

import com.staj.CarCommerceApp.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByUsername_ShouldReturnUser_WhenFound() {
        User user = new User();
        user.setUsername("Test");
        user.setPassword("Test");
        userRepository.save(user);

        var newUser = userRepository.findByUsername(user.getUsername());

        assertEquals(user.toString(), newUser.toString());
    }

    @Test
    void findByUsername_ShouldReturnNull_WhenNotFound() {
        var newUser = userRepository.findByUsername("Test");

        assertNull(newUser);
    }
}