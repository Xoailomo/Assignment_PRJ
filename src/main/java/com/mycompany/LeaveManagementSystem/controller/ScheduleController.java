/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.controller;

import com.mycompany.LeaveManagementSystem.model.Employee;
import com.mycompany.LeaveManagementSystem.model.LeaveRequest;
import com.mycompany.LeaveManagementSystem.model.LeaveStatus;
import com.mycompany.LeaveManagementSystem.model.Users;
import com.mycompany.LeaveManagementSystem.repository.EmployeeRepository;
import com.mycompany.LeaveManagementSystem.repository.LeaveRequestRepository;
import com.mycompany.LeaveManagementSystem.repository.UserRepository;

/**
 *
 * @author phank
 */
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    private final EmployeeRepository employeeRepository;
    private final LeaveRequestRepository leaveRequestRepository;

    @Autowired
    private UserRepository userRepository;

    public ScheduleController(EmployeeRepository employeeRepository, LeaveRequestRepository leaveRequestRepository) {
        this.employeeRepository = employeeRepository;
        this.leaveRequestRepository = leaveRequestRepository;
    }

    @GetMapping("/companycalendar")
    public String getSchedule(
            @RequestParam(required = false) String start,
            @RequestParam(required = false) String end,
            @RequestParam(defaultValue = "0") int page, // Tham số trang, mặc định là 0
            Model model) {

        // Xác định ngày bắt đầu và kết thúc
        LocalDate today = LocalDate.now();
        LocalDate startDate = (start != null) ? LocalDate.parse(start) : today;
        LocalDate endDate = (end != null) ? LocalDate.parse(end) : startDate.plusDays(6);

        // Thiết lập phân trang: 10 nhân viên mỗi trang
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page, pageSize);

        // Lấy danh sách nhân viên theo trang
        Page<Employee> employeePage = employeeRepository.findAll(pageable);
        List<Employee> employees = employeePage.getContent();

        // Tạo dữ liệu lịch làm việc cho từng nhân viên trong trang hiện tại
        Map<Employee, Map<LocalDate, String>> scheduleMap = new HashMap<>();
        for (Employee employee : employees) {
            Map<LocalDate, String> dailyStatus = new LinkedHashMap<>();
            for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
                dailyStatus.put(date, "WORKING"); // Mặc định là WORKING
            }
            List<LeaveRequest> leaves = leaveRequestRepository.findByEmployeeAndDateRange(employee, startDate, endDate);
            for (LeaveRequest leave : leaves) {
                LocalDate leaveStart = leave.getStartDate();
                LocalDate leaveEnd = leave.getEndDate();
                LeaveStatus status = leave.getStatus();
                for (LocalDate date = leaveStart; !date.isAfter(leaveEnd); date = date.plusDays(1)) {
                    switch (status) {
                        case APPROVED:
                            dailyStatus.put(date, "APPROVED_LEAVE");
                            break;
                        case REJECTED:
                            dailyStatus.put(date, "REJECTED_LEAVE");
                            break;
                        case INPROGRESS:
                            dailyStatus.put(date, "INPROGRESS_LEAVE");
                            break;
                        case CANCELLED:
                            dailyStatus.put(date, "CANCELLED_LEAVE");
                            break;
                    }
                }
            }
            scheduleMap.put(employee, dailyStatus);
        }

        // Truyền dữ liệu vào model
        model.addAttribute("schedule", scheduleMap);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", employeePage.getTotalPages());
        model.addAttribute("pageSize", pageSize);

        return "companycalendar"; // Tên của template Thymeleaf
    }

    @GetMapping("/mycalendar")
    public String getMyCalendar(
            @RequestParam(required = false, defaultValue = "2025") int year,
            Model model) {

        // Lấy thông tin người dùng hiện tại từ Spring Security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Tìm employeeId từ bảng User
        Users user = userRepository.findByUsername(username);
        int employeeId = user.getEmployee().getId();

        // Lấy thông tin nhân viên
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        // Lấy ngày hiện tại
        LocalDate currentDate = LocalDate.now();

        // Xác định khoảng thời gian cho cả năm
        LocalDate startOfYear = LocalDate.of(year, 1, 1);
        LocalDate endOfYear = LocalDate.of(year, 12, 31);

        // Tạo lịch cho cả năm
        Map<YearMonth, Map<Integer, String>> yearlySchedule = new HashMap<>();

        // Duyệt qua từng tháng trong năm
        for (int month = 1; month <= 12; month++) {
            YearMonth yearMonth = YearMonth.of(year, month);
            LocalDate startOfMonth = yearMonth.atDay(1);
            LocalDate endOfMonth = yearMonth.atEndOfMonth();

            // Khởi tạo trạng thái mặc định cho từng ngày trong tháng
            Map<Integer, String> dailyStatus = new LinkedHashMap<>();
            for (int day = 1; day <= yearMonth.lengthOfMonth(); day++) {
                dailyStatus.put(day, "WORKING");
            }

            // Lấy danh sách đơn nghỉ phép trong tháng
            List<LeaveRequest> leaves = leaveRequestRepository.findByEmployeeAndDateRange(employee, startOfMonth, endOfMonth);
            for (LeaveRequest leave : leaves) {
                LocalDate leaveStart = leave.getStartDate();
                LocalDate leaveEnd = leave.getEndDate();
                LeaveStatus status = leave.getStatus();

                // Chỉ xử lý các ngày trong tháng hiện tại
                for (LocalDate date = leaveStart; !date.isAfter(leaveEnd); date = date.plusDays(1)) {
                    if (date.getMonthValue() == month && date.getYear() == year) {
                        int day = date.getDayOfMonth();
                        switch (status) {
                            case APPROVED:
                                dailyStatus.put(day, "APPROVED_LEAVE");
                                break;
                            case REJECTED:
                                dailyStatus.put(day, "REJECTED_LEAVE");
                                break;
                            case INPROGRESS:
                                dailyStatus.put(day, "INPROGRESS_LEAVE");
                                break;
                            case CANCELLED:
                                dailyStatus.put(day, "CANCELLED_LEAVE");
                                break;
                        }
                    }
                }
            }
            yearlySchedule.put(yearMonth, dailyStatus);
        }

        // Truyền dữ liệu vào model
        model.addAttribute("yearlySchedule", yearlySchedule);
        model.addAttribute("year", year);
        model.addAttribute("employee", employee);
        model.addAttribute("startOfYear", startOfYear);
        model.addAttribute("endOfYear", endOfYear);
        model.addAttribute("currentDate", currentDate); // Thêm ngày hiện tại vào model

        return "mycalendar";
    }
}
