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
@Controller
public class LoginController {
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Trả về trang login.jsp hoặc login.html
    }
}

