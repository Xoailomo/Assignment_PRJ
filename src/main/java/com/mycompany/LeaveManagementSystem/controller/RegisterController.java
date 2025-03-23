/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.controller;

import com.mycompany.LeaveManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author phank
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(
            @RequestParam String email,
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam String password,
            @RequestParam String username,
            @RequestParam String displayName,
            @RequestParam int employeeId,
            Model model) {

        if (userService.emailExists(email)) {
            model.addAttribute("error", "Email already registered!");
            return "register";
        }

        userService.registerUser(username, email, firstname, lastname, displayName, password, employeeId);
        return "redirect:/login"; // Chuyển hướng đến trang login sau khi đăng ký thành công
    }
}
