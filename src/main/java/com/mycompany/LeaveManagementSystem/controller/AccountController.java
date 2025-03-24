/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.controller;

import com.mycompany.LeaveManagementSystem.dto.LoginDTO;
import com.mycompany.LeaveManagementSystem.dto.RegisterDTO;
import com.mycompany.LeaveManagementSystem.model.Employee;
import com.mycompany.LeaveManagementSystem.model.Users;
import com.mycompany.LeaveManagementSystem.repository.UserRepository;
import com.mycompany.LeaveManagementSystem.service.EmployeeService;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import jakarta.validation.Valid;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author phank
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Value("${security.jwt.secret-key}")
    private String jwtSecretKey;
    @Value("${security.jwt.issuer}")
    private String jwtIssuer;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private EmployeeService employeeService;
//**

    @GetMapping("/profile")
    public ResponseEntity<Object> profile(Authentication auth) {
        var resp = new HashMap<String, Object>();
        resp.put("username", auth.getName());
        resp.put("authorities", auth.getAuthorities());

        var user = userRepository.findByUsername(auth.getName());
        resp.put("user", user);
        return ResponseEntity.ok(resp);
    }
//**
//    @PostMapping("/register")
//    public ResponseEntity<Object> register(@Valid @RequestBody RegisterDTO rdto, BindingResult result) {
//        if (result.hasErrors()) {
//            var errorsList = result.getAllErrors();
//            var errorsMap = new HashMap<String, String>();
//
//            for (int i = 0; i < errorsList.size(); i++) {
//                var error = (FieldError) errorsList.get(i);
//                errorsMap.put(error.getField(), error.getDefaultMessage());
//
//            }
//            return ResponseEntity.badRequest().body(errorsMap);
//        }
//
//        var bCryptEncoder = new BCryptPasswordEncoder();
//
//        Users user = new Users();
//        user.setFirstName(rdto.getFirstname());
//        user.setLastName(rdto.getLastname());
//        user.setUsername(rdto.getUsername());   
//        user.setEmail(rdto.getEmail());
//        user.setRole("staff");
//        user.setCreateAt(new Date());
//        user.setPassword(bCryptEncoder.encode(rdto.getPassword()));
//
//        try {
//            //check if username/email are used or not 
//            var otherUser = userRepository.findByUsername(rdto.getUsername());
//            if (otherUser != null) {
//                return ResponseEntity.badRequest().body("Username already used");
//
//            }
//            otherUser = userRepository.findByEmail(rdto.getEmail());
//            if (otherUser != null) {
//                return ResponseEntity.badRequest().body("Email adress already used");
//
//            }
//            userRepository.save(user);
//
//            String jwtToken = createJwtToken(user);
//
//            var resp = new HashMap<String, Object>();
//            resp.put("token", jwtToken);
//            resp.put("user", user);
//            return ResponseEntity.ok(resp);
//
//        } catch (Exception e) {
//            System.out.println("There is an Exception: ");
//            e.printStackTrace();
//        }
//        return ResponseEntity.badRequest().body("Error");
//    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody RegisterDTO rdto, BindingResult result) {
        if (result.hasErrors()) {
            var errorsMap = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return ResponseEntity.badRequest().body(errorsMap);
        }

        var bCryptEncoder = new BCryptPasswordEncoder();

        // Kiểm tra nếu username hoặc email đã tồn tại
        if (userRepository.findByUsername(rdto.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already used");
        }

        if (userRepository.findByEmail(rdto.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email address already used");
        }

        Users user = new Users();
        user.setFirstName(rdto.getFirstname());
        user.setLastName(rdto.getLastname());
        user.setUsername(rdto.getUsername());
        user.setEmail(rdto.getEmail());
        user.setRole("staff");
        user.setCreateAt(new Date());
        user.setPassword(bCryptEncoder.encode(rdto.getPassword()));

        try {
            userRepository.save(user);
            String jwtToken = createJwtToken(user);

            var response = Map.of(
                    "token", jwtToken,
                    "user", user
            );

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error registering user");
        }
    }

//**
    @PostMapping("/login")
public ResponseEntity<Object> login(@Valid @RequestBody LoginDTO ldto, BindingResult result) {
    if (result.hasErrors()) {
        var errorsMap = result.getAllErrors().stream()
                .collect(Collectors.toMap(
                        error -> ((FieldError) error).getField(),
                        ObjectError::getDefaultMessage
                ));
        return ResponseEntity.badRequest().body(errorsMap);
    }
    
    try {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(ldto.getUsername(), ldto.getPassword()));
        
        Optional<Users> optionalUser = userRepository.findByUsername(ldto.getUsername());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().body("Bad username or password");
        }

        Users user = optionalUser.get();
        String jwtToken = createJwtToken(user);

        var resp = new HashMap<String, Object>();
        resp.put("token", jwtToken);
        resp.put("user", user);
        return ResponseEntity.ok(resp);

    } catch (Exception e) {
        System.out.println("There is an exception: ");
        e.printStackTrace();
    }
    return ResponseEntity.badRequest().body("Bad username or password");
}


    @GetMapping("/my-account")
    public String myAccountPage(Model model) {
        // Lấy email của user từ session (Spring Security)
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }

        Employee currentEmployee = employeeService.getEmployeeByEmail(email);
        List<Employee> staffs = employeeService.getStaffsByManager(currentEmployee.getId());

        model.addAttribute("employee", currentEmployee);
        model.addAttribute("staffs", staffs);

        return "my-account";
    }

    //test with code from youtube
    private String createJwtToken(Users user) {
        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(jwtIssuer)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(24 * 3600))
                .subject(user.getUsername())
                .build();

        var encoder = new NimbusJwtEncoder(
                new ImmutableSecret<>(jwtSecretKey.getBytes()));

        var params = JwtEncoderParameters.from(
                JwsHeader.with(MacAlgorithm.HS256).build(), claims);
        return encoder.encode(params).getTokenValue();
    }
}
