/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.model;

import jakarta.persistence.*;
import java.sql.Date;

/**
 *
 * @author phank
 */
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;
    private String address;
    private String status;
    private String gender;
    private Date createdAt;
    
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "eid", nullable = true) // Cho phép null nếu user không thuộc employee nào
//    @JsonIgnore
//    private Employee employee;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonIgnore
//    private Set<UserRole> userRoles;
//
//    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonIgnore
//    private Set<LeaveRequest> createdRequests;
//
//    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonIgnore
//    private Set<LeaveRequest> ownedRequests;
//
//    @OneToMany(mappedBy = "processBy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonIgnore
//    private Set<LeaveRequest> processedRequests;
//
//    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonIgnore
//    private Set<Holiday> holidays;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonIgnore
//    private Set<Notification> notifications;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonIgnore
//    private Set<PasswordResetToken> passwordResetTokens;
//
//    @OneToMany(mappedBy = "approver", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonIgnore
//    private Set<LeaveRequestApproval> approvals;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonIgnore
//    private Set<AuditLog> auditLogs;
    // Getters và Setters
    public void setCreatedAt(Date createdAt) {    
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public Employee getEmployee() {
//        return employee;
//    }
//
//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//    }
//
//    public Set<UserRole> getUserRoles() {
//        return userRoles;
//    }
//
//    public void setUserRoles(Set<UserRole> userRoles) {
//        this.userRoles = userRoles;
//    }
//
//    public Set<LeaveRequest> getCreatedRequests() {
//        return createdRequests;
//    }
//
//    public void setCreatedRequests(Set<LeaveRequest> createdRequests) {
//        this.createdRequests = createdRequests;
//    }
//
//    public Set<LeaveRequest> getOwnedRequests() {
//        return ownedRequests;
//    }
//
//    public void setOwnedRequests(Set<LeaveRequest> ownedRequests) {
//        this.ownedRequests = ownedRequests;
//    }
//
//    public Set<LeaveRequest> getProcessedRequests() {
//        return processedRequests;
//    }
//
//    public void setProcessedRequests(Set<LeaveRequest> processedRequests) {
//        this.processedRequests = processedRequests;
//    }
//
//    public Set<Holiday> getHolidays() {
//        return holidays;
//    }
//
//    public void setHolidays(Set<Holiday> holidays) {
//        this.holidays = holidays;
//    }
//
//    public Set<Notification> getNotifications() {
//        return notifications;
//    }
//
//    public void setNotifications(Set<Notification> notifications) {
//        this.notifications = notifications;
//    }
//
//    public Set<PasswordResetToken> getPasswordResetTokens() {
//        return passwordResetTokens;
//    }
//
//    public void setPasswordResetTokens(Set<PasswordResetToken> passwordResetTokens) {
//        this.passwordResetTokens = passwordResetTokens;
//    }
//
//    public Set<LeaveRequestApproval> getApprovals() {
//        return approvals;
//    }
//
//    public void setApprovals(Set<LeaveRequestApproval> approvals) {
//        this.approvals = approvals;
//    }
//
//    public Set<AuditLog> getAuditLogs() {
//        return auditLogs;
//    }
//
//    public void setAuditLogs(Set<AuditLog> auditLogs) {
//        this.auditLogs = auditLogs;
//    }
}
