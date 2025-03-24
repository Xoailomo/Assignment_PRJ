/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.repository;

import com.mycompany.LeaveManagementSystem.model.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author phank
 */
public interface UserRepository extends JpaRepository<Users, Integer>{

    Optional<Users> findByUsername(String username);

    Optional<Users>  findByEmail(String email);
//    public Users  findByEmail(String email);

    public boolean existsByUsername(String username);

    public boolean existsByEmail(String email);
    
    
}
