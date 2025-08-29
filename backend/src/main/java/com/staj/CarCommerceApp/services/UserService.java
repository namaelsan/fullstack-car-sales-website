package com.staj.CarCommerceApp.services;

import com.staj.CarCommerceApp.models.User;
import com.staj.CarCommerceApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTService jwtService;

    private BCryptPasswordEncoder encoder= UserRepository.encoder;


    public User registerUser(User user) {
        String username = user.getUsername();
        if (userRepository.findByUsername(username) != null) {
            return null;
        }

        user.setPassword(encoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public String verifyUser(User user) {
        System.out.println(user.toString());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        if (!authentication.isAuthenticated()) {
            return null;
        }
        return jwtService.generateJWTToken(user.getUsername(),user.getPassword());
    }
}
