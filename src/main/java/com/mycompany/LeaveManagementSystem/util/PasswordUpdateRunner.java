package com.mycompany.LeaveManagementSystem.util;

import com.mycompany.LeaveManagementSystem.model.LeaveRequest;
import com.mycompany.LeaveManagementSystem.repository.LeaveRequestRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUpdateRunner implements CommandLineRunner {
@Autowired
    private LeaveRequestRepository leaveRequestRepo;
    /**
     *
     * @param args
     */
    @Override
    public void run(String... args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Mã hóa mật khẩu
        System.out.println("Mật khẩu đã mã hóa:");
        System.out.println("123 -> " + encoder.encode("123"));
        System.out.println("456 -> " + encoder.encode("456"));
        System.out.println("789 -> " + encoder.encode("789"));
List<LeaveRequest> requests = leaveRequestRepo.findAll();
        System.out.println(requests);
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//
//        // Mật khẩu gốc
//        String rawPassword = "123";
//        // Mật khẩu đã mã hóa từ DB (copy từ database)
//        String hashedPassword = "$2a$10$a47/LNK0OgA/os3VOYPUKepX/wg13Ri8qF360XX5rjgG5tfk/x11e";
//
//        boolean isMatch = encoder.matches(rawPassword, hashedPassword);
//        System.out.println("Mật khẩu đúng: " + isMatch);
    }
}
