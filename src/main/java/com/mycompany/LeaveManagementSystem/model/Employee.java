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
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "Employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "eName", nullable = false, length = 255)
    private String name;

    @Column(name = "hireDate", nullable = false)
    private LocalDate hireDate;

    @Column(name = "status", nullable = false, length = 50)
    private String status = "Active";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deptid", nullable = false)
    private Department department;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<EmployeeHierarchy> subordinates;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<EmployeeHierarchy> managers;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<LeaveBalance> leaveBalances;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<LeaveEntitlement> leaveEntitlements;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;

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

    // Getter và Setter cho hireDate
    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    // Getter và Setter cho status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Getter và Setter cho department
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    // Getter và Setter cho subordinates
    public Set<EmployeeHierarchy> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(Set<EmployeeHierarchy> subordinates) {
        this.subordinates = subordinates;
    }

    // Getter và Setter cho managers
    public Set<EmployeeHierarchy> getManagers() {
        return managers;
    }

    public void setManagers(Set<EmployeeHierarchy> managers) {
        this.managers = managers;
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

    // Getter và Setter cho user
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
