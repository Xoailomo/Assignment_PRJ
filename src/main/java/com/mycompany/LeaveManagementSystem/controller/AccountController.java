/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.controller;

import com.mycompany.LeaveManagementSystem.dto.LoginDTO;
import com.mycompany.LeaveManagementSystem.dto.RegisterDTO;
import com.mycompany.LeaveManagementSystem.model.Users;
import com.mycompany.LeaveManagementSystem.repository.UserRepository;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import jakarta.validation.Valid;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
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
    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody RegisterDTO rdto, BindingResult result) {
        if (result.hasErrors()) {
            var errorsList = result.getAllErrors();
            var errorsMap = new HashMap<String, String>();

            for (int i = 0; i < errorsList.size(); i++) {
                var error = (FieldError) errorsList.get(i);
                errorsMap.put(error.getField(), error.getDefaultMessage());

            }
            return ResponseEntity.badRequest().body(errorsMap);
        }

        var bCryptEncoder = new BCryptPasswordEncoder();

        Users user = new Users();
        user.setFirstName(rdto.getFirstname());
        user.setLastName(rdto.getLastname());
        user.setUsername(rdto.getUsername());
        user.setEmail(rdto.getEmail());
        user.setRole("staff");
        user.setCreateAt(new Date());
        user.setPassword(bCryptEncoder.encode(rdto.getPassword()));

        try {
            //check if username/email are used or not 
            var otherUser = userRepository.findByUsername(rdto.getUsername());
            if (otherUser != null) {
                return ResponseEntity.badRequest().body("Username already used");

            }
            otherUser = userRepository.findByEmail(rdto.getEmail());
            if (otherUser != null) {
                return ResponseEntity.badRequest().body("Email adress already used");

            }
            userRepository.save(user);

            String jwtToken = createJwtToken(user);

            var resp = new HashMap<String, Object>();
            resp.put("token", jwtToken);
            resp.put("user", user);
            return ResponseEntity.ok(resp);

        } catch (Exception e) {
            System.out.println("There is an Exception: ");
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("Error");
    }
//**
    // test with code form youtube
    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginDTO ldto, BindingResult result) {
        if (result.hasErrors()) {
            var errorsList = result.getAllErrors();
            var errorsMap = new HashMap<String, String>();

            for (int i = 0; i < errorsList.size(); i++) {
                var error = (FieldError) errorsList.get(i);
                errorsMap.put(error.getField(), error.getDefaultMessage());

            }
            return ResponseEntity.badRequest().body(errorsMap);
        }
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(ldto.getUsername(), ldto.getPassword()));
            Users user = userRepository.findByUsername(ldto.getUsername());

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
