/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.controller;

/**
 *
 * @author phank
 */

import com.mycompany.LeaveManagementSystem.model.Users;
import com.mycompany.LeaveManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/my-account")
    public String getUserAccount(Model model) {
        // Lấy email từ phiên đăng nhập (Spring Security)
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = null;

        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        }

        if (email == null) {
            return "redirect:/login"; // Chưa đăng nhập, chuyển hướng về login
        }

        // Lấy user từ database
        Users user = userService.getUserByEmail(email);
        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("user", user);
        return "my-account"; // Trả về template Thymeleaf
    }
}
