/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.LeaveManagementSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 *
 * @author phank
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("/")
    public String homePage() {
        return "index";
    }
}

// LoginController.java
@Controller
public class LoginController {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    
    @PostMapping("/login")
    public String handleLogin(@RequestParam String username, @RequestParam String password) {
        String sql = "SELECT COUNT(*) FROM Users WHERE username = ? AND password = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, username, password);
        
        if (count != null && count > 0) {
            return "redirect:/dashboard";
        }
        return "redirect:/login?error";
    }
}

//// LeaveRequestController.java
//@Controller
//public class LeaveRequestController {
//    
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//    
//    @GetMapping("/leave-request")
//    public String leaveRequestPage() {
//        return "leave-request";
//    }
//    
//    @PostMapping("/leave-request")
//    public String submitLeaveRequest(@RequestParam String employeeName, @RequestParam String startDate, @RequestParam String endDate) {
//        String sql = "INSERT INTO LeaveRequests (employee_name, start_date, end_date) VALUES (?, ?, ?)";
//        jdbcTemplate.update(sql, employeeName, startDate, endDate);
//        return "redirect:/leave-request?success";
//    }
}

