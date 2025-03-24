/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.security;

/**
 *
 * @author phank
 */
import com.mycompany.LeaveManagementSystem.model.CustomUserDetails;
import com.mycompany.LeaveManagementSystem.model.Users;
import com.mycompany.LeaveManagementSystem.repository.UserRepository;
import java.util.Optional;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepo;

    public CustomUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username)
                .map(user -> User.withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().split(","))
                .build()
                ).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

}
