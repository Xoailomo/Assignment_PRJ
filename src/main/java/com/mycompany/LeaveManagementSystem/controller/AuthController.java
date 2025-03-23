/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.controller;

/**
 *
 * @author phank
 */

import com.mycompany.LeaveManagementSystem.model.JwtResponse;
import com.mycompany.LeaveManagementSystem.model.LoginRequest;
import com.mycompany.LeaveManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

//    @Autowired
//    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
//        try {
//            authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
//            );
//        } catch (Exception e) {
//            return ResponseEntity.status(401).body("Thông tin đăng nhập không hợp lệ");
//        }
//
//        String token = jwtUtil.generateToken(loginRequest.getUsername());
//        return ResponseEntity.ok(new JwtResponse(token));
//    }
}