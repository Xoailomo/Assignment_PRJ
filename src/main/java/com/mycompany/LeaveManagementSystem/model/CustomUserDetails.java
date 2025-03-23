/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.model;

/**
 *
 * @author phank
 */
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Đóng gói UserEntity vào UserDetails
 */
public class CustomUserDetails implements UserDetails {

    private final Users user;

    public CustomUserDetails(Users user) {
        this.user = user;
    }

    // Lấy authorities từ cột 'role'
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // role trong DB: "ROLE_ADMIN" => new SimpleGrantedAuthority("ROLE_ADMIN")
        return List.of(new SimpleGrantedAuthority(user.getRole()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // Nếu bạn muốn hiển thị displayName trong Thymeleaf: #authentication.principal.displayName
    public String getDisplayName() {
        return user.getDisplayName();
    }

    // Còn lại cho đơn giản
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

