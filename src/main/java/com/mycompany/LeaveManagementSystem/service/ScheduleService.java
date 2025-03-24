/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.service;

import com.mycompany.LeaveManagementSystem.dto.WorkScheduleDTO;
import com.mycompany.LeaveManagementSystem.model.Employee;
import com.mycompany.LeaveManagementSystem.model.LeaveRequest;
import com.mycompany.LeaveManagementSystem.model.LeaveType;
import com.mycompany.LeaveManagementSystem.repository.EmployeeRepository;
import com.mycompany.LeaveManagementSystem.repository.LeaveRequestRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author phank
 */
@Service
public class ScheduleService {
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    public List<WorkScheduleDTO> getWorkSchedules(LocalDate startDate, LocalDate endDate) {
        List<Employee> employees = employeeRepository.findAll();
        List<WorkScheduleDTO> scheduleList = new ArrayList<>();

       for (Employee emp : employees) {
    for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
        boolean isOnLeave = false;
        LeaveType leaveType = null;  // Đổi từ String sang LeaveType

        List<LeaveRequest> leaves = leaveRequestRepository.findByEmployeeAndDate(emp, date);
        if (!leaves.isEmpty()) {
            isOnLeave = true;
            leaveType = leaves.get(0).getLeaveType();  // Lấy loại nghỉ đầu tiên
        }

        scheduleList.add(new WorkScheduleDTO(emp.getFullName(), date, !isOnLeave, leaveType));
    }
}
        return scheduleList;
    }
}

