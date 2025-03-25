/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.dto;

import com.mycompany.LeaveManagementSystem.model.LeaveTypes;
import java.time.LocalDate;
import lombok.*;

/**
 *
 * @author phank
 */
@Getter
@Setter
public class WorkScheduleDTO {

    private String employeeName;
    private LocalDate date;
    private boolean isWorking; // true = làm việc, false = nghỉ phép
    private LeaveTypes leaveType; // Optional: Loại nghỉ (Annual, Sick Leave, etc.)

    public WorkScheduleDTO() {
    }

    public WorkScheduleDTO(String employeeName, LocalDate date, boolean isWorking, LeaveTypes leaveType) {
        this.employeeName = employeeName;
        this.date = date;
        this.isWorking = isWorking;
        this.leaveType = leaveType;
    }
    
}
