/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.repository;

import com.mycompany.LeaveManagementSystem.model.Employee;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author phank
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
     public Employee findByEmail(String email);

    public Optional<Employee> findById(Long employeeId);
    
    List<Employee> findByManagerId(int managerId);

}
