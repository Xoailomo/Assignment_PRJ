package com.mycompany.LeaveManagementSystem.controller;

import com.mycompany.LeaveManagementSystem.model.Users;
import com.mycompany.LeaveManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class LoginController {

//    private final AuthenticationManager authenticationManager;
////    private final JwtUtil jwtUtil;
//    private final UserService userService;
//
////    @Autowired
////    public LoginController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService) {
////        this.authenticationManager = authenticationManager;
////        this.jwtUtil = jwtUtil;
////        this.userService = userService;
////    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
//        String username = loginRequest.get("username");
//        String password = loginRequest.get("password");
//
//        // Xác thực user
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//
//        // Nếu xác thực thành công, lấy user từ DB
//        Users user = userService.getUserByUsername(username);
//        String token = jwtUtil.generateToken(user.getUsername());
//
//        Map<String, String> response = new HashMap<>();
//        response.put("token", token);
//        return ResponseEntity.ok(response);
//    }
}
