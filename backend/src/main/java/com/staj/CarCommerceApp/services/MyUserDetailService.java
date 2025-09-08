package com.staj.CarCommerceApp.services;

import com.staj.CarCommerceApp.entities.Role;
import com.staj.CarCommerceApp.entities.User;
import com.staj.CarCommerceApp.models.UserPrincipal;
import com.staj.CarCommerceApp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found: " + username);
        }

        return new UserPrincipal(user);
    }

    private Set<GrantedAuthority> getAuthorities(Set<Role> roles) {
        System.out.println("USERROLE BEING SET RN"+ roles.stream().map(Role::getRoleName));
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName()))
                .collect(Collectors.toSet());
    }
}
