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

@Entity
@Table(name = "LeaveEntitlements")
public class LeaveEntitlement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeId", nullable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leaveTypeId", nullable = false)
    private LeaveType leaveType;

    @Column(name = "entitledDays", nullable = false)
    private Double entitledDays;

    @Column(name = "effectiveDate", nullable = false)
    private LocalDate effectiveDate;

    // Getter và Setter cho id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter và Setter cho employee
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    // Getter và Setter cho leaveType
    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    // Getter và Setter cho entitledDays
    public Double getEntitledDays() {
        return entitledDays;
    }

    public void setEntitledDays(Double entitledDays) {
        this.entitledDays = entitledDays;
    }

    // Getter và Setter cho effectiveDate
    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
