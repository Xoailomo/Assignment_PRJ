/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.LeaveManagementSystem.model;

/**
 *
 * @author phank
 */

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "AuditLogs")
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "action", nullable = false, length = 255)
    private String action;

    @Column(name = "details", columnDefinition = "TEXT")
    private String details;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    // Getter và Setter cho id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter và Setter cho action
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    // Getter và Setter cho details
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    // Getter và Setter cho timestamp
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    // Getter và Setter cho user
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
