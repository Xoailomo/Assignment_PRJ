/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.controller;

import com.mycompany.LeaveManagementSystem.dto.WorkScheduleDTO;
import com.mycompany.LeaveManagementSystem.model.Employee;
import com.mycompany.LeaveManagementSystem.model.LeaveRequest;
import com.mycompany.LeaveManagementSystem.repository.EmployeeRepository;
import com.mycompany.LeaveManagementSystem.repository.LeaveRequestRepository;
import com.mycompany.LeaveManagementSystem.service.ScheduleService;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author phank
 */
@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @GetMapping("/schedule")
    public String getSchedule(@RequestParam(required = false) String start,
            @RequestParam(required = false) String end,
            Model model) {
        LocalDate today = LocalDate.now();
        LocalDate startDate = (start != null) ? LocalDate.parse(start) : today;
        LocalDate endDate = (end != null) ? LocalDate.parse(end) : startDate.plusDays(6); // 7 ngày tính từ start

        List<Employee> employees = employeeRepository.findAll();
        Map<Employee, Map<LocalDate, String>> scheduleMap = new HashMap<>();

        for (Employee employee : employees) {
            Map<LocalDate, String> dailyStatus = new LinkedHashMap<>();
            for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
                dailyStatus.put(date, "WORKING"); // Mặc định là đi làm
            }

            List<LeaveRequest> leaves = leaveRequestRepository.findByEmployeeAndDateRange(employee, startDate, endDate);
            for (LeaveRequest leave : leaves) {
                LocalDate leaveStart = leave.getStartDate();
                LocalDate leaveEnd = leave.getEndDate();
                for (LocalDate date = leaveStart; !date.isAfter(leaveEnd); date = date.plusDays(1)) {
                    dailyStatus.put(date, "ON_LEAVE"); // Ghi nhận nghỉ phép
                }
            }

            scheduleMap.put(employee, dailyStatus);
        }

        model.addAttribute("schedule", scheduleMap);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        return "schedule";
    }

}
