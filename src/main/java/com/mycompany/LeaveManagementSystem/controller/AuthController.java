/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.controller;

import com.mycompany.LeaveManagementSystem.model.Roles;
import com.mycompany.LeaveManagementSystem.model.Users;
import com.mycompany.LeaveManagementSystem.repository.UserRepository;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // login.html (Thymeleaf)
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new Users());
        return "register"; // register.html (Thymeleaf)
    }

    @PostMapping("/register")
    public String doRegister(
            @ModelAttribute("user") Users user,
            @RequestParam String password,
            Model model) {
        // Kiểm tra username đã tồn tại chưa
        if (userRepo.existsByUsername(user.getUsername())) {
            model.addAttribute("error", "Username already exists!");
            return "register";
        }

        // Kiểm tra email đã tồn tại chưa
        if (userRepo.existsByEmail(user.getEmail())) {
            model.addAttribute("error", "Email already exists!");
            return "register";
        }

        // Tạo user mới
        user.setPassword(passwordEncoder.encode(password)); // Mã hóa mật khẩu
        user.setCreateAt(new Date()); // Gán ngày tạo hiện tại

        // Gán vai trò mặc định (ROLE_STAFF)
        Set<Roles> roles = new HashSet<>();
        Roles staffRole = new Roles();
        staffRole.setRname("ROLE_STAFF");
        roles.add(staffRole);
        user.setRoles(roles);

        // Lưu user vào cơ sở dữ liệu
        userRepo.save(user);

        // Sau khi đăng ký xong -> chuyển về login
        return "redirect:/login?success";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword() {
        return "forgot-password"; // forgot-password.html (Thymeleaf)
    }
}