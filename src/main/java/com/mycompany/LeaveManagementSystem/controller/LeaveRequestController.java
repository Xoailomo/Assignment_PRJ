package com.mycompany.LeaveManagementSystem.controller;

import com.mycompany.LeaveManagementSystem.model.Employee;
import com.mycompany.LeaveManagementSystem.model.LeaveRequest;
import com.mycompany.LeaveManagementSystem.model.LeaveStatus;
import com.mycompany.LeaveManagementSystem.model.Users;
import com.mycompany.LeaveManagementSystem.repository.EmployeeRepository;
import com.mycompany.LeaveManagementSystem.repository.LeaveRequestRepository;
import com.mycompany.LeaveManagementSystem.service.LeaveRequestService;
import com.mycompany.LeaveManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/leave-requests")
public class LeaveRequestController {

    @Autowired
    private LeaveRequestRepository leaveRequestRepo;

    @Autowired
    private LeaveRequestService leaveRequestService;

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private UserService userService;

    // Lấy form tạo đơn nghỉ (sử dụng Thymeleaf view)
    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        return new ModelAndView("leave/create", "leaveRequest", new LeaveRequest());
    }
    @PostMapping("/create")
    public ResponseEntity<?> submitLeaveRequest(
            @RequestBody LeaveRequest leaveRequest, // Lấy dữ liệu JSON
            Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Not login yet!"));
        }
        Users currentUser = userService.getUserByUsername(principal.getName());
        if (currentUser.getEmployee() == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Employee does not exist for " + currentUser.getUsername()));
        }

        // Gắn employee
        leaveRequest.setEmployee(currentUser.getEmployee());

        // Ghi vào DB
        leaveRequestService.createLeaveRequest(leaveRequest);

        // Trả về JSON
        return ResponseEntity.ok(Map.of("message", "Submit successfully!"));
    }

    // Hiển thị danh sách đơn nghỉ của user hiện tại
    @GetMapping("/my-requests")
    public String myRequests(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        // Lấy employee dựa trên email (giả sử email của user trùng với employee.email)
        Employee employee = employeeRepo.findByEmail(principal.getName());
        if (employee == null) {
            return "error/403";
        }
        List<LeaveRequest> requests = leaveRequestRepo.findByEmployee(employee);
        model.addAttribute("requests", requests);
        return "leave/my-request";
    }

   

    // Endpoint để hủy đơn nghỉ
    @GetMapping("/cancel/{id}")
    public ResponseEntity<?> cancelLeave(@PathVariable int id) {
        LeaveRequest request = leaveRequestRepo.findById(id).orElse(null);
        if (request != null) {
            // Chỉ cho phép hủy đơn nếu trạng thái vẫn INPROGRESS
            if (request.getStatus() == LeaveStatus.INPROGRESS) {
                request.setStatus(LeaveStatus.CANCELLED);
                leaveRequestRepo.save(request);
                return ResponseEntity.ok("Leave request cancelled successfully.");
            } else {
                return ResponseEntity.badRequest().body("Only pending requests can be cancelled.");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Leave request not found.");
    }

    // Endpoint để gửi reminder (tạm thời trả về message)
    @GetMapping("/reminder/{id}")
    public ResponseEntity<?> sendReminder(@PathVariable int id) {
        LeaveRequest request = leaveRequestRepo.findById(id).orElse(null);
        if (request != null) {
            // Ở đây bạn có thể gọi email service, tạm thời trả về message
            return ResponseEntity.ok("Reminder sent successfully to manager.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Leave request not found.");
    }

    // Các endpoint approve và reject vẫn giữ nguyên (nếu cần)
    @GetMapping("/approve/{id}")
    public String approveLeave(@PathVariable int id) {
        LeaveRequest request = leaveRequestRepo.findById(id).orElse(null);
        if (request != null) {
            request.setStatus(LeaveStatus.APPROVED);
            leaveRequestRepo.save(request);
        }
        return "redirect:/leave-requests/request-list";
    }

    @GetMapping("/reject/{id}")
    public String rejectLeave(@PathVariable int id) {
        LeaveRequest request = leaveRequestRepo.findById(id).orElse(null);
        if (request != null) {
            request.setStatus(LeaveStatus.REJECTED);
            leaveRequestRepo.save(request);
        }
        return "redirect:/leave-requests/request-list";
    }

    @GetMapping("/request-list")
    public String requestList(Model model) {
        var requests = leaveRequestRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("requests", requests);
        return "leave/request-list"; // Giao diện danh sách nhân viên
    
    }

}
