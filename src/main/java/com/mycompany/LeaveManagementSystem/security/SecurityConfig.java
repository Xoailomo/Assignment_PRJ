package com.mycompany.LeaveManagementSystem.security;

import com.mycompany.LeaveManagementSystem.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${security.jwt.secret-key}")
    private String jwtSecretKey;

    private final JwtFilter jwtFilter;
    private final UserDetailsService userDetailsService;

    public SecurityConfig(JwtFilter jwtFilter, UserDetailsService userDetailsService) {
        this.jwtFilter = jwtFilter;
        this.userDetailsService = userDetailsService;
    }

    // Cấu hình phân quyền
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Tắt CSRF (nếu không cần)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login", "/register", "/css/**", "/js/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN") // => "ROLE_ADMIN"
                .requestMatchers("/manager/**").hasAnyRole("MANAGER")
                .requestMatchers("/staff/**").hasAnyRole("STAFF")
                .anyRequest().authenticated()
                )
                .formLogin(form -> form
                .loginPage("/login") // URL trang login (GET)
                .loginProcessingUrl("/login") // URL xử lý login (POST)
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error=true")
                .permitAll()
                )
                .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll()
                )
                .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                );

        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withSecretKey(new SecretKeySpec(jwtSecretKey.getBytes(), "HmacSHA256")).build();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserService userService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());

        return new ProviderManager(List.of(provider)); // Hỗ trợ mở rộng nhiều provider sau này
    }

    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                    Authentication authentication) throws IOException {
                // Lấy danh sách roles của người dùng
                var authorities = authentication.getAuthorities();

                // Chuyển hướng dựa trên role
                if (authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                    response.sendRedirect("/admin/");
                } else if (authorities.contains(new SimpleGrantedAuthority("ROLE_MANAGER"))) {
                    response.sendRedirect("/manager/");
                } else if (authorities.contains(new SimpleGrantedAuthority("ROLE_STAFF"))) {
                    response.sendRedirect("/staff/");
                } else {
                    response.sendRedirect("/"); // Trường hợp không xác định
                }
            }
        };
    }

}
