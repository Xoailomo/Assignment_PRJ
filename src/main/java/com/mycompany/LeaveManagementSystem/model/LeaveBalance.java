/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.LeaveManagementSystem.model;
import java.time.LocalDate;
/**
 *
 * @author phank
 */
import jakarta.persistence.*;

@Entity
@Table(name = "LeaveBalances")
public class LeaveBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeId", nullable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leaveTypeId", nullable = false)
    private LeaveType leaveType;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "allowedDays", nullable = false)
    private Double allowedDays;

    @Column(name = "availableDays", nullable = false)
    private Double availableDays;

    @Column(name = "usedDays", nullable = false)
    private Double usedDays = 0.0;

    @Column(name = "resetDate", nullable = false)
    private LocalDate resetDate;

    // Getter và Setter cho id
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    // Getter và Setter cho year
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    // Getter và Setter cho allowedDays
    public Double getAllowedDays() {
        return allowedDays;
    }

    public void setAllowedDays(Double allowedDays) {
        this.allowedDays = allowedDays;
    }

    // Getter và Setter cho availableDays
    public Double getAvailableDays() {
        return availableDays;
    }

    public void setAvailableDays(Double availableDays) {
        this.availableDays = availableDays;
    }

    // Getter và Setter cho usedDays
    public Double getUsedDays() {
        return usedDays;
    }

    public void setUsedDays(Double usedDays) {
        this.usedDays = usedDays;
    }

    // Getter và Setter cho resetDate
    public LocalDate getResetDate() {
        return resetDate;
    }

    public void setResetDate(LocalDate resetDate) {
        this.resetDate = resetDate;
    }
}