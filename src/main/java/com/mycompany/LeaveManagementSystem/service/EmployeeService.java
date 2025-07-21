/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.service;

/**
 *
 * @author phank
 */

import com.mycompany.LeaveManagementSystem.model.Employees;
import com.mycompany.LeaveManagementSystem.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employees> getStaffsByManager(int eid) {
        return employeeRepository.findByManagerEid(eid);
    }
}

