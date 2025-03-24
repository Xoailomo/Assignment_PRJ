/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.service;


import com.mycompany.LeaveManagementSystem.model.Users;
import com.mycompany.LeaveManagementSystem.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

/**
 *
 * @author phank
 */
@Service
@Primary  // Đánh dấu bean này là ưu tiên
public class UserDetailsServiceImpl implements UserDetailsService {
//
    @Autowired
    private UserRepository userRepository;  
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Users user = userRepository.findByUsername(username);
//
//        if (user != null) {
//            var springUser = User.withUsername(user.getUsername())
//                    .password(user.getPassword())
//                    .roles(user.getRole().split(",")) // Truyền role vào đây
//                    .build();
//            return springUser;
//        }
//        return null;
//
//    }
    @Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username)
            .map(user -> User.withUsername(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRole().split(","))
                    .build()
            ).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
}

}
