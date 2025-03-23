package com.mycompany.LeaveManagementSystem.controller;

import com.mycompany.LeaveManagementSystem.model.Employee;
import com.mycompany.LeaveManagementSystem.model.LeaveRequest;
import com.mycompany.LeaveManagementSystem.model.LeaveStatus;
import com.mycompany.LeaveManagementSystem.model.User;
import com.mycompany.LeaveManagementSystem.repository.EmployeeRepository;
import com.mycompany.LeaveManagementSystem.repository.LeaveRequestRepository;
import com.mycompany.LeaveManagementSystem.service.LeaveRequestService;
import com.mycompany.LeaveManagementSystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;
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

//    // Nhận POST qua AJAX, trả về JSON
//    @PostMapping("/create")
//    public ResponseEntity<?> submitLeaveRequest(
//            @Valid @RequestBody LeaveRequest leaveRequest,
//            Principal principal) {
//        if (principal == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .body(Map.of("message", "Not login yet!"));
//        }
//        User currentUser = userService.getUserByUsername(principal.getName());
//        if (currentUser.getEmployee() == null) {
//            return ResponseEntity.badRequest()
//                    .body(Map.of("message", "Employee does not exist for " + currentUser.getUsername()));
//        }
//        leaveRequest.setEmployee(currentUser.getEmployee());
//        leaveRequestService.createLeaveRequest(leaveRequest);
//        return ResponseEntity.ok(Map.of("message", "Submit successfully!"));
//    }
@PostMapping("/create")
    public ResponseEntity<?> submitLeaveRequest(
            @RequestBody LeaveRequest leaveRequest, // Lấy dữ liệu JSON
            Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Not login yet!"));
        }
        User currentUser = userService.getUserByUsername(principal.getName());
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
    
    // Hiển thị danh sách đơn nghỉ của user hiện tại (trả về JSON)
    @GetMapping("/my-requests")
    @ResponseBody
    public ResponseEntity<?> myRequests(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Not login yet!"));
        }
        // Giả sử email của user trùng với employee.email
        Employee employee = employeeRepo.findByEmail(principal.getName());
        if (employee == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Employee not found for " + principal.getName()));
        }
        List<LeaveRequest> requests = leaveRequestRepo.findByEmployee(employee);
        return ResponseEntity.ok(Map.of("requests", requests));
    }

    // Endpoint để lấy danh sách đơn nghỉ của tất cả người dùng (cho Admin hoặc purpose khác)
    @GetMapping("/request-list")
    @ResponseBody
    public ResponseEntity<?> requestList(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Not logged in"));
        }
        List<LeaveRequest> requests = leaveRequestRepo.findAll();
        return ResponseEntity.ok(Map.of("requests", requests));
    }

    // Endpoint để hủy đơn nghỉ (trả về JSON)
    @GetMapping("/cancel/{id}")
    @ResponseBody
    public ResponseEntity<?> cancelLeave(@PathVariable int id) {
        LeaveRequest request = leaveRequestRepo.findById(id).orElse(null);
        if (request != null) {
            if (request.getStatus() == LeaveStatus.INPROGRESS) {
                request.setStatus(LeaveStatus.CANCELLED);
                leaveRequestRepo.save(request);
                return ResponseEntity.ok(Map.of("message", "Leave request cancelled successfully."));
            } else {
                return ResponseEntity.badRequest()
                        .body(Map.of("message", "Only pending requests can be cancelled."));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", "Leave request not found."));
    }

    // Endpoint để gửi reminder (trả về JSON)
    @GetMapping("/reminder/{id}")
    @ResponseBody
    public ResponseEntity<?> sendReminder(@PathVariable int id) {
        LeaveRequest request = leaveRequestRepo.findById(id).orElse(null);
        if (request != null) {
            // Ở đây bạn có thể gọi email service; tạm thời trả về message
            return ResponseEntity.ok(Map.of("message", "Reminder sent successfully to manager."));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", "Leave request not found."));
    }

    // Endpoint approve đơn nghỉ (trả về JSON)
    @GetMapping("/approve/{id}")
    @ResponseBody
    public ResponseEntity<?> approveLeave(@PathVariable int id) {
        LeaveRequest request = leaveRequestRepo.findById(id).orElse(null);
        if (request != null) {
            request.setStatus(LeaveStatus.APPROVED);
            leaveRequestRepo.save(request);
            return ResponseEntity.ok(Map.of("message", "Leave request approved."));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", "Leave request not found."));
    }

    // Endpoint reject đơn nghỉ (trả về JSON)
    @GetMapping("/reject/{id}")
    @ResponseBody
    public ResponseEntity<?> rejectLeave(@PathVariable int id) {
        LeaveRequest request = leaveRequestRepo.findById(id).orElse(null);
        if (request != null) {
            request.setStatus(LeaveStatus.REJECTED);
            leaveRequestRepo.save(request);
            return ResponseEntity.ok(Map.of("message", "Leave request rejected."));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", "Leave request not found."));
    }
}
