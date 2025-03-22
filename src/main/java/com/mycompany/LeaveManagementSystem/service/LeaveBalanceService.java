/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.LeaveManagementSystem.service;

/**
 *
 * @author phank
 */

import com.mycompany.LeaveManagementSystem.model.LeaveBalance;
import com.mycompany.LeaveManagementSystem.repository.LeaveBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveBalanceService {

    @Autowired
    private LeaveBalanceRepository repository;

    public List<LeaveBalance> getUserBalances(int employeeId) {
        return repository.findByEmployeeId(employeeId);
    }
}