/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.dto;

import com.mycompany.LeaveManagementSystem.model.LeaveType;
import java.time.LocalDate;

/**
 *
 * @author phank
 */
public class WorkScheduleDTO {
     private String employeeName;
    private LocalDate date;
    private boolean isWorking; // true = làm việc, false = nghỉ phép
    private LeaveType leaveType; // Optional: Loại nghỉ (Annual, Sick Leave, etc.)

    public WorkScheduleDTO(String employeeName, LocalDate date, boolean isWorking, LeaveType leaveType) {
        this.employeeName = employeeName;
        this.date = date;
        this.isWorking = isWorking;
        this.leaveType = leaveType;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isIsWorking() {
        return isWorking;
    }

    public void setIsWorking(boolean isWorking) {
        this.isWorking = isWorking;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }
    
    
}
