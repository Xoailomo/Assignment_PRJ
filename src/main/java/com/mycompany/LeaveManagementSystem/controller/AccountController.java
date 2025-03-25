/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.controller;

import com.mycompany.LeaveManagementSystem.dto.LoginDTO;
import com.mycompany.LeaveManagementSystem.dto.RegisterDTO;
import com.mycompany.LeaveManagementSystem.model.Employees;
import com.mycompany.LeaveManagementSystem.model.Features;
import com.mycompany.LeaveManagementSystem.model.Roles;
import com.mycompany.LeaveManagementSystem.model.Users;
import com.mycompany.LeaveManagementSystem.repository.UserRepository;
import com.mycompany.LeaveManagementSystem.service.EmployeeService;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import jakarta.validation.Valid;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/profile")
    public ResponseEntity<Object> profile(Authentication auth) {
        if (auth == null || !auth.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }

        var resp = new HashMap<String, Object>();
        resp.put("username", auth.getName());
        resp.put("authorities", auth.getAuthorities());

        Optional<Users> userOpt = userRepository.findByUsername(auth.getName());
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        resp.put("user", userOpt.get());
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody RegisterDTO rdto, BindingResult result) {
        if (result.hasErrors()) {
            var errorsMap = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return ResponseEntity.badRequest().body(errorsMap);
        }

        // Kiểm tra nếu username hoặc email đã tồn tại
        if (userRepository.findByUsername(rdto.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already used");
        }

        if (userRepository.findByEmail(rdto.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email address already used");
        }

        Users user = new Users();
        user.setUsername(rdto.getUsername());
        user.setDisplayname(rdto.getFirstname() + " " + rdto.getLastname());
        user.setEmail(rdto.getEmail());
        user.setCreateAt(new Date());
        user.setPassword(passwordEncoder.encode(rdto.getPassword()));

        // Gán vai trò mặc định (ROLE_STAFF)
        Set<Roles> roles = new HashSet<>();
        Roles staffRole = new Roles();
        staffRole.setRname("ROLE_STAFF");
        roles.add(staffRole);
        user.setRoles(roles);

        // Gán features (nếu cần)
        user.setFeatures(new HashSet<>()); // Có thể thêm logic để gán features mặc định

        try {
            userRepository.save(user);
            String jwtToken = createJwtToken(user);

            var response = Map.of(
                    "token", jwtToken,
                    "user", user
            );

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error registering user: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginDTO ldto, BindingResult result) {
        if (result.hasErrors()) {
            var errorsMap = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return ResponseEntity.badRequest().body(errorsMap);
        }

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(ldto.getUsername(), ldto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

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
            return ResponseEntity.badRequest().body("Bad username or password: " + e.getMessage());
        }
    }

    @GetMapping("/my-account")
    public String myAccountPage(Model model) {
        // Lấy thông tin người dùng hiện tại từ Spring Security
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return "redirect:/login";
        }

        String username = auth.getName();
        Optional<Users> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            return "redirect:/login?error=userNotFound";
        }

        Users currentUser = userOpt.get();
        Employees currentEmployee = currentUser.getEmployee();
        if (currentEmployee == null) {
            return "redirect:/login?error=noEmployee";
        }

        // Lấy danh sách nhân viên báo cáo trực tiếp
        List<Employees> directReports = employeeService.getStaffsByManager(currentEmployee.getEid());

        model.addAttribute("employee", currentEmployee);
        model.addAttribute("staffs", directReports);

        return "my-account";
    }

    private String createJwtToken(Users user) {
        Instant now = Instant.now();

        // Xử lý danh sách roles (giả sử Roles có trường 'name')
        List<String> roleNames = user.getRoles() != null
                ? user.getRoles().stream()
                        .filter(role -> role.getRname() != null)
                        .map(Roles::getRname)
                        .collect(Collectors.toList())
                : Collections.emptyList();

        // Xử lý danh sách features (sử dụng 'url' thay vì 'name')
        List<String> featureUrls = user.getFeatures() != null
                ? user.getFeatures().stream()
                        .filter(feature -> feature.getUrl() != null)
                        .map(Features::getUrl)
                        .collect(Collectors.toList())
                : Collections.emptyList();

        // Tạo claims cho JWT
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(jwtIssuer)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(24 * 3600)) // Hết hạn sau 24 giờ
                .subject(user.getUsername())
                .claim("roles", roleNames)
                .claim("features", featureUrls) // Sử dụng 'url' từ Features
                .build();

        // Mã hóa token
        var encoder = new NimbusJwtEncoder(
                new ImmutableSecret<>(jwtSecretKey.getBytes()));

        var params = JwtEncoderParameters.from(
                JwsHeader.with(MacAlgorithm.HS256).build(), claims);
        return encoder.encode(params).getTokenValue();
    }
}
