package com.mycompany.LeaveManagementSystem.security;

//import com.mycompany.LeaveManagementSystem.model.User;
//import com.mycompany.LeaveManagementSystem.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

//@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Autowired
//    @Lazy
//    private UserService userService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/login", "/css/**", "/js/**").permitAll() // Allow login page & static resources
//                .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
//                .loginPage("/login") // Custom login page URL
//                .loginProcessingUrl("/perform_login") // URL for form submission
//                .defaultSuccessUrl("/dashboard", true) // Redirect after successful login
//                .failureUrl("/login?error=true") // Redirect after failure
//                .permitAll()
//                )
//                .logout(logout -> logout
//                .logoutUrl("/logout") // URL to trigger logout
//                .logoutSuccessUrl("/login?logout=true") // Redirect after logout
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID")
//                .permitAll()
//                )
//                .csrf(csrf -> csrf.disable()); // Disable CSRF for testing (enable in production)
//
//        return http.build();

//test with sample code from youtube
        return http
                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/").permitAll()//default to home
                .requestMatchers("/login").permitAll()
                .anyRequest().authenticated()
                ).formLogin(form -> form
                .defaultSuccessUrl("/", true)
                )
                .logout(config -> config.logoutSuccessUrl("/"))
                .build();

    }

//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//        UserDetails user = User.withUsername("sa")
//                .password(passwordEncoder.encode("123"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user); // Using in-memory authentication for now
//    }
    @Bean
public UserDetailsService userDetailsService() {
    UserDetails user = User.withDefaultPasswordEncoder()
        .username("user")
        .password("password")
        .roles("USER")
        .build();
    return new InMemoryUserDetailsManager(user);
}


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
