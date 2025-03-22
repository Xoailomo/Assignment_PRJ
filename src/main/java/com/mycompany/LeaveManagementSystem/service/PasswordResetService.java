/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.service;

/**
 *
 * @author phank
 */
import com.mycompany.LeaveManagementSystem.model.PasswordResetToken;
import com.mycompany.LeaveManagementSystem.model.User;
import com.mycompany.LeaveManagementSystem.repository.PasswordResetTokenRepository;
import com.mycompany.LeaveManagementSystem.repository.UserRepository;
import com.mycompany.LeaveManagementSystem.util.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.util.Base64;
import java.time.LocalDateTime;

@Service
public class PasswordResetService {

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailSender emailSender; // Gửi email

    private static final String BASE_URL = "http://localhost:8080/reset-password?token="; // Link reset

    public void sendPasswordResetEmail(String email) {
        // Kiểm tra user theo email
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("Email not found");
        }

        // Tạo token
        String token = generateRandomToken();

        // Lưu vào database
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setId(user.getId());
        resetToken.setToken(token);
        resetToken.setExpiryDate(LocalDateTime.now().plusDays(1));

        tokenRepository.save(resetToken);

        // Gửi email
        String resetLink = BASE_URL + token;
        emailSender.sendResetPasswordEmail(email, resetLink);
    }

    private String generateRandomToken() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}
