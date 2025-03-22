package com.mycompany.LeaveManagementSystem.service;

import com.mycompany.LeaveManagementSystem.model.User;
import com.mycompany.LeaveManagementSystem.repository.UserRepository;
import com.mycompany.LeaveManagementSystem.util.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // Sử dụng BCrypt để mã hóa

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    public boolean authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            System.out.println("User không tồn tại");
            return false;
        }
        boolean isMatch = passwordEncoder.matches(password, user.getPassword());
        if (!isMatch) {
            System.out.println("Sai mật khẩu");
        }
        return isMatch;
    }

    public void registerUser(User user) {
        // Mã hóa mật khẩu trước khi lưu
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    public void sendResetPasswordEmail(String email) {
        User user = userRepository.findByEmail(email); // FIX: tìm theo email
        if (user != null) {
            String token = UUID.randomUUID().toString();
            LocalDateTime expiryDate = LocalDateTime.now().plusHours(1);
            userRepository.saveResetToken(user.getId(), token, expiryDate.toString());
            String resetLink = "http://localhost:9999/reset-password?token=" + token;
            emailSender.sendResetPasswordEmail(user.getEmail(), resetLink);
        } else {
            throw new RuntimeException("User not found with email: " + email);
        }
    }

}
