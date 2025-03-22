package com.mycompany.LeaveManagementSystem.security;

import com.mycompany.LeaveManagementSystem.model.User;
import com.mycompany.LeaveManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.stream.Collectors;
import org.springframework.context.annotation.Lazy;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    @Lazy
    private UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable()) // Nếu dùng API, cần tắt CSRF
//                .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/login", "/forgot-password", "/css/**").permitAll()
//                .requestMatchers("/home", "/my-agenda", "/my-request", "/my-account").hasAnyRole("MANAGER", "STAFF")
//                .requestMatchers("/company-agenda", "/request-list").hasRole("MANAGER")
//                .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
//                .loginPage("/login")
//                .defaultSuccessUrl("/home", true)
//                .permitAll()
//                )
//                .logout(logout -> logout
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login?logout")
//                .permitAll()
//                );
//        return http.build();
        http
        .csrf(csrf -> csrf.disable()) // Tắt CSRF nếu dùng API
        .authorizeHttpRequests(auth -> auth
            .anyRequest().permitAll() // 🚀 Cho phép tất cả request không cần login
        );
    return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            User user = userService.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User not found: " + username);
            }

            // Lấy danh sách roles từ UserRole
            String[] roles = user.getUserRoles().stream()
                    .map(userRole -> userRole.getRole().getName())
                    .collect(Collectors.toList())
                    .toArray(new String[0]);

            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword()) // Đảm bảo mật khẩu đã được mã hóa
                    .roles(roles) // Dùng danh sách roles từ UserRole
                    .build();
        };
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
