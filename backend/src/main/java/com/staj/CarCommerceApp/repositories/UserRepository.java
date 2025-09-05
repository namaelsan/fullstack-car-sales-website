package com.staj.CarCommerceApp.repositories;

import com.staj.CarCommerceApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
}
