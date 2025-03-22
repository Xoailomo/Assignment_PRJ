/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.LeaveManagementSystem.repository;

/**
 *
 * @author phank
 */

import com.mycompany.LeaveManagementSystem.model.Employee;
import com.mycompany.LeaveManagementSystem.model.LeaveBalance;
import com.mycompany.LeaveManagementSystem.model.LeaveType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LeaveBalanceRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<LeaveBalance> findByEmployeeId(int employeeId) {
        String sql = "SELECT * FROM LeaveBalances WHERE employeeId = ?";
        return jdbcTemplate.query(sql, this::mapRowToLeaveBalance, employeeId);
    }

    private LeaveBalance mapRowToLeaveBalance(ResultSet rs, int rowNum) throws SQLException {
        LeaveBalance balance = new LeaveBalance();
        balance.setId(rs.getInt("id"));
         // Ánh xạ Employee từ employeeId
        Employee employee = new Employee();
        employee.setId(rs.getInt("employeeId"));
        balance.setEmployee(employee); 
        
       // Ánh xạ LeaveType từ leaveTypeId
        LeaveType leaveType = new LeaveType();
        leaveType.setId(rs.getInt("leaveTypeId"));
        balance.setLeaveType(leaveType);

        balance.setYear(rs.getInt("year"));
        balance.setAllowedDays(rs.getDouble("allowedDays"));
        balance.setAvailableDays(rs.getDouble("availableDays"));
        balance.setUsedDays(rs.getDouble("usedDays"));
        balance.setResetDate(rs.getDate("resetDate").toLocalDate());
        return balance;
    }
}