package com.mycompany.LeaveManagementSystem.repository;

import com.mycompany.LeaveManagementSystem.model.Roles;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByRname(String rname); 
}
