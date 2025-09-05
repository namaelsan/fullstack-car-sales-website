package com.staj.CarCommerceApp.services;

import com.staj.CarCommerceApp.entities.Role;
import com.staj.CarCommerceApp.entities.User;
import com.staj.CarCommerceApp.repositories.RoleRepository;
import com.staj.CarCommerceApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

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

        if (userRepository.count() == 0) {
            user.setRoles(roleRepository.findAll());
        }else {
            user.setRoles(roleRepository.findAllByRoleName("USER"));
        }

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

    public User getUserById (Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User addUserRole(Long userId, Long roleId) {
        User user = getUserById(userId);
        if (user == null) return null;

        Role role = roleRepository.findById(roleId).orElse(null);
        if (role == null) return null;

        List<Role> roles = user.getRoles();
        roles.add(role);

        user.setRoles(roles);


        if (!user.getRoles().contains(role)) {
            return null;
        }
        return userRepository.save(user);
    }

    public boolean deleteUserById(Long id) {
        if (!userRepository.findById(id).isPresent())
            return false;

        userRepository.deleteById(id);
        if (userRepository.findById(id).isPresent()) {
            return false;
        }
        return true;
    }
}





