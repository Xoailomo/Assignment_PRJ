/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.controller;

import com.mycompany.LeaveManagementSystem.model.Employees;
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
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
            @RequestParam(defaultValue = "0") int page, // Phân trang, mặc định là trang 0
            Model model) {

        // Xác định ngày bắt đầu và kết thúc với xử lý lỗi
        LocalDate today = LocalDate.now();
        LocalDate startDate, endDate;
        try {
            startDate = (start != null) ? LocalDate.parse(start) : today;
            endDate = (end != null) ? LocalDate.parse(end) : startDate.plusDays(6);
        } catch (DateTimeParseException e) {
            model.addAttribute("error", "Invalid date format!");
            return "companycalendar"; // Trả về trang với thông báo lỗi
        }

        // Thiết lập phân trang: 10 nhân viên mỗi trang
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page, pageSize);

        // Lấy danh sách nhân viên theo trang
        Page<Employees> employeePage = employeeRepository.findAll(pageable);
        List<Employees> employees = employeePage.getContent();

        // Lấy tất cả đơn nghỉ của nhân viên trong khoảng thời gian này (tối ưu query)
        List<LeaveRequest> leaveRequests = leaveRequestRepository.findByEmployeesAndDateRange(employees, startDate, endDate);

        // Tạo Map lưu trạng thái lịch làm việc của từng nhân viên
        Map<Employees, Map<LocalDate, String>> scheduleMap = new HashMap<>();

        for (Employees employee : employees) {
            Map<LocalDate, String> dailyStatus = new LinkedHashMap<>();

            // Đặt mặc định tất cả ngày là "WORKING"
            for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
                dailyStatus.put(date, "WORKING");
            }

            // Lọc ra các đơn nghỉ của nhân viên hiện tại
            List<LeaveRequest> employeeLeaves = leaveRequests.stream()
                    .filter(leave -> leave.getEmployee().equals(employee))
                    .collect(Collectors.toList());

            // Cập nhật trạng thái ngày nghỉ
            for (LeaveRequest leave : employeeLeaves) {
                for (LocalDate date = leave.getStartDate(); !date.isAfter(leave.getEndDate()); date = date.plusDays(1)) {
                    dailyStatus.put(date, getLeaveStatus(leave.getStatus())); // Sử dụng method riêng
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

        return "companycalendar"; // Trả về template Thymeleaf
    }

// Phương thức lấy trạng thái đơn nghỉ
    private String getLeaveStatus(LeaveStatus status) {
        switch (status) {
            case APPROVED:
                return "APPROVED_LEAVE";
            case REJECTED:
                return "REJECTED_LEAVE";
            case INPROGRESS:
                return "INPROGRESS_LEAVE";
            case CANCELLED:
                return "CANCELLED_LEAVE";
            default:
                return "UNKNOWN";
        }
    }

    @GetMapping("/mycalendar")
    public String getMyCalendar(
            @RequestParam(required = false, defaultValue = "2025") int year,
            Model model) {

        // Lấy thông tin người dùng hiện tại từ Spring Security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Tìm người dùng theo username
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        // Kiểm tra employee của user
        Employees employee = Optional.ofNullable(user.getEmployee())
                .orElseThrow(() -> new IllegalArgumentException("User does not have an associated employee."));

        // Lấy ngày hiện tại
        LocalDate currentDate = LocalDate.now();
        LocalDate startOfYear = LocalDate.of(year, 1, 1);
        LocalDate endOfYear = LocalDate.of(year, 12, 31);

        // Tạo lịch cho cả năm
        Map<YearMonth, Map<Integer, String>> yearlySchedule = new HashMap<>();

        // Lấy danh sách đơn nghỉ phép của nhân viên trong năm
        List<LeaveRequest> leaveRequests = leaveRequestRepository.findByEmployeeAndDateRange(employee, startOfYear, endOfYear);

        // Duyệt qua từng tháng trong năm
        for (int month = 1; month <= 12; month++) {
            YearMonth yearMonth = YearMonth.of(year, month);
            Map<Integer, String> dailyStatus = new LinkedHashMap<>();

            // Mặc định là "WORKING"
            for (int day = 1; day <= yearMonth.lengthOfMonth(); day++) {
                dailyStatus.put(day, "WORKING");
            }

            // Cập nhật trạng thái nghỉ phép
            for (LeaveRequest leave : leaveRequests) {
                LocalDate leaveStart = leave.getStartDate();
                LocalDate leaveEnd = leave.getEndDate();
                LeaveStatus status = leave.getStatus();

                for (LocalDate date = leaveStart; !date.isAfter(leaveEnd); date = date.plusDays(1)) {
                    if (date.getMonthValue() == month && date.getYear() == year) {
                        dailyStatus.put(date.getDayOfMonth(), getLeaveStatus(status));
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
        model.addAttribute("currentDate", currentDate);

        return "mycalendar";
    }

}
