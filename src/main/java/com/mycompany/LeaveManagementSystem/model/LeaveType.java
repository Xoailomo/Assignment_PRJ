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
import java.util.Set;

@Entity
@Table(name = "LeaveType")
public class LeaveType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @OneToMany(mappedBy = "leaveType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<LeaveBalance> leaveBalances;

    @OneToMany(mappedBy = "leaveType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<LeaveEntitlement> leaveEntitlements;

    @OneToMany(mappedBy = "leaveType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<LeaveRequest> leaveRequests;

    // Getter và Setter cho id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter và Setter cho name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter và Setter cho leaveBalances
    public Set<LeaveBalance> getLeaveBalances() {
        return leaveBalances;
    }

    public void setLeaveBalances(Set<LeaveBalance> leaveBalances) {
        this.leaveBalances = leaveBalances;
    }

    // Getter và Setter cho leaveEntitlements
    public Set<LeaveEntitlement> getLeaveEntitlements() {
        return leaveEntitlements;
    }

    public void setLeaveEntitlements(Set<LeaveEntitlement> leaveEntitlements) {
        this.leaveEntitlements = leaveEntitlements;
    }

    // Getter và Setter cho leaveRequests
    public Set<LeaveRequest> getLeaveRequests() {
        return leaveRequests;
    }

    public void setLeaveRequests(Set<LeaveRequest> leaveRequests) {
        this.leaveRequests = leaveRequests;
    }
}
