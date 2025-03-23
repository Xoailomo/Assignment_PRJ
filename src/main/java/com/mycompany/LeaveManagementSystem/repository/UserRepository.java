/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.repository;

import com.mycompany.LeaveManagementSystem.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author phank
 */
public interface UserRepository extends JpaRepository<User, Integer>{

    public User findByEmail(String email);

    public boolean existsByUsername(String username);

    public boolean existsByEmail(String email);
    
    Optional<User> findByUsername(String username);
    
}
