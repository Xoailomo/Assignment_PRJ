/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.service;

import com.mycompany.LeaveManagementSystem.dto.WorkScheduleDTO;
import com.mycompany.LeaveManagementSystem.model.Employees;
import com.mycompany.LeaveManagementSystem.model.LeaveRequest;
import com.mycompany.LeaveManagementSystem.model.LeaveTypes;
import com.mycompany.LeaveManagementSystem.repository.EmployeeRepository;
import com.mycompany.LeaveManagementSystem.repository.LeaveRequestRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        List<Employees> employees = employeeRepository.findAll();
        List<WorkScheduleDTO> scheduleList = new ArrayList<>();

        // Lấy toàn bộ đơn nghỉ của nhân viên trong khoảng thời gian trước
        List<LeaveRequest> allLeaveRequests = leaveRequestRepository.findByEmployeesAndDateRange(employees, startDate, endDate);

        // Lưu đơn nghỉ theo từng nhân viên để tối ưu tìm kiếm
        Map<Employees, List<LeaveRequest>> leaveMap = new HashMap<>();
        for (LeaveRequest leave : allLeaveRequests) {
            leaveMap.computeIfAbsent(leave.getEmployee(), k -> new ArrayList<>()).add(leave);
        }

        // Duyệt danh sách nhân viên và tạo lịch làm việc
        for (Employees emp : employees) {
            List<LeaveRequest> empLeaves = leaveMap.getOrDefault(emp, new ArrayList<>());

            for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
                boolean isOnLeave = false;
                LeaveTypes leaveType = null;

                for (LeaveRequest leave : empLeaves) {
                    if (!date.isBefore(leave.getStartDate()) && !date.isAfter(leave.getEndDate())) {
                        isOnLeave = true;
                        leaveType = leave.getLeaveType();
                        break;
                    }
                }

                scheduleList.add(new WorkScheduleDTO(emp.getEname(), date, !isOnLeave, leaveType));
            }
        }
        return scheduleList;
    }
}


