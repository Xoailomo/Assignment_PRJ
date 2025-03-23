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
import com.mycompany.LeaveManagementSystem.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    
    @Autowired
    private UserRepository userRepo;
    
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // login.html (Thymeleaf)
    }
    
    @GetMapping("/register")
    public String registerPage() {
        return "register"; // register.html (Thymeleaf)
    }
    
    @PostMapping("/register")
    public String doRegister(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String displayName,
                             @RequestParam String email,
                             @RequestParam Date createAt,
                             Model model) {
        // Kiểm tra username đã tồn tại chưa
        if (userRepo.existsByUsername(username)) {
            model.addAttribute("error", "Username already exists!");
            return "register";
        }
        // Tạo user mới
        Users user = new Users();
        user.setUsername(username);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setRole("ROLE_STAFF"); // mặc định staff
        user.setDisplayName(displayName);
        user.setEmail(email);
        user.setCreateAt(createAt);
        
        userRepo.save(user);
        
        // Sau khi đăng ký xong -> chuyển về login
        return "redirect:/login?success";
    }
}

