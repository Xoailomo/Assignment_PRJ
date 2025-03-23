package com.mycompany.LeaveManagementSystem.service;

import com.mycompany.LeaveManagementSystem.model.Users;
import com.mycompany.LeaveManagementSystem.repository.UserRepository;
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
    public Users getUserByUsername(String username) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username);

        if (user != null) {
            var springUser = User.withUsername(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRoles().split(",")) // Truyền role vào đây
                    .build();
            return springUser;
        }
        return null;

    }

}
