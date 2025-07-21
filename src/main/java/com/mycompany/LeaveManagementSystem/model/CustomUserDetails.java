package com.mycompany.LeaveManagementSystem.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Đóng gói UserEntity vào UserDetails
 */
public class CustomUserDetails implements UserDetails {

    private final Users user;
    private final List<String> roles; // Danh sách role của user

    public CustomUserDetails(Users user, List<String> roles) {
        this.user = user;
        this.roles = roles;
    }

    // Lấy authorities từ danh sách roles trong DB
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(SimpleGrantedAuthority::new) // Chuyển từng role thành GrantedAuthority
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public String getDisplayName() {
        return user.getDisplayname();
    }

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
