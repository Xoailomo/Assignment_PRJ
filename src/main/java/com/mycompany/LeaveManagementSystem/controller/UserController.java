package com.mycompany.LeaveManagementSystem.controller;

import com.mycompany.LeaveManagementSystem.model.Users;
import com.mycompany.LeaveManagementSystem.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder = null;
//    private final JwtUtil jwtUtil = null;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@RequestBody Users user) {
//        try {
//            Users newUser = userService.registerUser(
//                    user.getUsername(),
//                    user.getEmail(),
//                    user.getFirstName(),
//                    user.getLastName(),
//                    user.getDisplayName(),
//                    user.getPassword(),
//                    user.getEmployee() != null ? user.getEmployee().getId() : 0
//            );
//            return ResponseEntity.ok("Users registered successfully!");
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

//    @PostMapping("/login")
//    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginRequest) {
//        String username = loginRequest.get("username");
//        String password = loginRequest.get("password");
//
//        Users user = userService.getUserByUsername(username);
//        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
//            return ResponseEntity.status(401).body("Invalid credentials");
//        }
//
////        String token = jwtUtil.generateToken(username);
////        Map<String, String> response = new HashMap<>();
////        response.put("token", token);
////        return ResponseEntity.ok(response);
//    }
}
