package com.mycompany.LeaveManagementSystem.service;

import com.mycompany.LeaveManagementSystem.model.Users;
import com.mycompany.LeaveManagementSystem.repository.UserRepository;
import java.util.Optional;
//import com.mycompany.LeaveManagementSystem.model.Users;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private RoleRepository roleRepository;
//    public void registerNewUser(UserRegistrationDTO dto) {
//        // Kiểm tra email đã tồn tại chưa
//        if (userRepository.existsByEmail(dto.getEmail())) {
//            throw new RuntimeException("Email đã được sử dụng");
//        }
//
//        // Tạo đối tượng Users mới
//        Users user = new Users();
//        user.setUsername(dto.getUsername());
//        user.setEmail(dto.getEmail());
//        user.setPassword(passwordEncoder.encode(dto.getPassword()));
//
//        // Gán vai trò mặc định "STAFF"
//        Role defaultRole = roleRepository.findByName("STAFF")
//                .orElseThrow(() -> new RuntimeException("Không tìm thấy vai trò STAFF"));
//        user.setRoles(Collections.singleton(defaultRole));
//
//        // Lưu người dùng vào cơ sở dữ liệu
//        userRepository.save(user);
//    }
//
    public Users getUserByEmail(String email) {
        Optional<Users> user = userRepository.findByEmail(email);
        return user.orElse(null); // Trả về null nếu không tìm thấy
    }

    public Users getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> User.withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().split(","))
                .build()
                ).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

}
