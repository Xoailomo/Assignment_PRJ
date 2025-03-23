package com.mycompany.LeaveManagementSystem.service;

import com.mycompany.LeaveManagementSystem.model.User;
import com.mycompany.LeaveManagementSystem.model.Employee;
import com.mycompany.LeaveManagementSystem.model.Role;
import com.mycompany.LeaveManagementSystem.repository.UserRepository;
import com.mycompany.LeaveManagementSystem.repository.EmployeeRepository;
import com.mycompany.LeaveManagementSystem.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UserService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;  // Không tạo BCryptPasswordEncoder() mới

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public User registerUser(String username, String email, String firstName, String lastName, String displayName, String password, int employeeId) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username already exists!");
        }

        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already exists!");
        }

        // Tạo User mới
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDisplayName((displayName == null || displayName.isEmpty()) ? firstName + " " + lastName : displayName);
        user.setPassword(passwordEncoder.encode(password)); // Mã hóa đúng cách

        // Gán Employee nếu có
        Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);
        if (employeeOpt.isPresent()) {
            user.setEmployee(employeeOpt.get());
        } else {
            throw new RuntimeException("Employee not found!");
        }

        // Gán Role mặc định là "STAFF"
        Role defaultRole = roleRepository.findByName("STAFF").orElseThrow(() -> new RuntimeException("Role not found!"));
        user.setRoles(Collections.singleton(defaultRole));

        return userRepository.save(user);
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
    
   
}
