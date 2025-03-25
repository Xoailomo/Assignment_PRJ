/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.repository;

import com.mycompany.LeaveManagementSystem.model.Employees;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author phank
 */
public interface EmployeeRepository extends JpaRepository<Employees, Integer> {
     public Employees findByEmail(String email);

    public Optional<Employees> findById(Long employeeId);
    
    List<Employees> findByManagerId(int managerId);
    List<Employees> findByEmployee(Employees employee);
    List<Employees> findByManager(Employees manager);

}
