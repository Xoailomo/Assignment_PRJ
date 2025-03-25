package com.mycompany.LeaveManagementSystem.controller;

import com.mycompany.LeaveManagementSystem.model.*;
import com.mycompany.LeaveManagementSystem.repository.EmployeeRepository;
import com.mycompany.LeaveManagementSystem.repository.LeaveRequestRepository;
import com.mycompany.LeaveManagementSystem.service.LeaveRequestService;
import com.mycompany.LeaveManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

    // Display form for creating a new leave request (Thymeleaf view)
    @GetMapping("/create")
    @PreAuthorize("hasAuthority('CREATE_LEAVE')")
    public ModelAndView showCreateForm() {
        return new ModelAndView("leave/create", "leaveRequest", new LeaveRequest());
    }

    // Submit a leave request via AJAX (JSON input/output)
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE_LEAVE')")
    public ResponseEntity<?> submitLeaveRequest(@RequestBody LeaveRequest leaveRequest, Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Not logged in!"));
        }

        // Retrieve the currently authenticated user
        Users currentUser = userService.getUserByUsername(principal.getName());

        // Ensure that the user has an associated employee record
        if (currentUser.getEmployee() == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "User does not have an associated employee record."));
        }

        // Validate that the leave start date is not after the end date
        if (leaveRequest.getStartDate() == null || leaveRequest.getEndDate() == null
                || leaveRequest.getStartDate().isAfter(leaveRequest.getEndDate())) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Invalid leave period. Check the start and end dates."));
        }

        // Attach the employee to the leave request
        leaveRequest.setEmployee(currentUser.getEmployee());
        leaveRequest.setStatus(LeaveStatus.INPROGRESS);

        // Persist the leave request
        leaveRequestService.createLeaveRequest(leaveRequest);

        return ResponseEntity.ok(Map.of("message", "Leave request submitted successfully!"));
    }

    // Display the list of leave requests for the current user (Thymeleaf view)
    @GetMapping("/my-request")
    @PreAuthorize("hasAuthority('VIEW_MY_REQUESTS')")
    public String myRequests(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }

        Users currentUser = userService.getUserByUsername(principal.getName());
        if (currentUser.getEmployee() == null) {
            return "redirect:/access-denied";
        }

        List<LeaveRequest> requests = leaveRequestRepo.findByEmployee(currentUser.getEmployee());
        model.addAttribute("requests", requests);
        return "leave/my-request";
    }

    // Endpoint to cancel a leave request (only if the request is still "INPROGRESS")
    @PostMapping("/cancel/{id}")
    @PreAuthorize("hasAuthority('CANCEL_LEAVE')")
    public ResponseEntity<?> cancelLeave(@PathVariable int id, Principal principal) {
        Optional<LeaveRequest> optionalRequest = leaveRequestRepo.findById(id);
        if (optionalRequest.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Leave request not found.");
        }
        LeaveRequest request = optionalRequest.get();

        // Ensure that the current user is the owner of the request
        Users currentUser = userService.getUserByUsername(principal.getName());
        if (!request.getEmployee().equals(currentUser.getEmployee())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("You can only cancel your own requests.");
        }

        if (request.getStatus() != LeaveStatus.INPROGRESS) {
            return ResponseEntity.badRequest()
                    .body("Only pending requests can be cancelled.");
        }

        request.setStatus(LeaveStatus.CANCELLED);
        leaveRequestRepo.save(request);
        return ResponseEntity.ok("Leave request cancelled successfully.");
    }

    // Endpoint to send a reminder for a leave request
    @PostMapping("/reminder/{id}")
    @PreAuthorize("hasAuthority('SEND_REMINDER')")
    public ResponseEntity<?> sendReminder(@PathVariable int id) {
        Optional<LeaveRequest> optionalRequest = leaveRequestRepo.findById(id);
        if (optionalRequest.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Leave request not found.");
        }
        // In a real application, call the email service here
        return ResponseEntity.ok("Reminder sent successfully to manager.");
    }

    // Admin/Manager endpoints for approving a leave request
    @PostMapping("/approve/{id}")
    @PreAuthorize("hasAuthority('APPROVE_LEAVE')")
    public ResponseEntity<?> approveLeave(@PathVariable int id, Principal principal) {
        Optional<LeaveRequest> optionalRequest = leaveRequestRepo.findById(id);
        if (optionalRequest.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Leave request not found.");
        }
        LeaveRequest request = optionalRequest.get();

        // Check if the current user is the manager of the employee who submitted the request
        Users approver = userService.getUserByUsername(principal.getName());
        Employees employee = request.getEmployee();
        List<Employees> hierarchy = employeeRepo.findByEmployee(employee);

        boolean isManager = hierarchy.stream()
                .anyMatch(h -> h.getManager().equals(approver.getEmployee()));
        if (!isManager) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("You are not authorized to approve this leave request.");
        }

        request.setStatus(LeaveStatus.APPROVED);
        leaveRequestRepo.save(request);
        return ResponseEntity.ok("Leave request approved.");
    }

    // Admin/Manager endpoints for rejecting a leave request
    @PostMapping("/reject/{id}")
    @PreAuthorize("hasAuthority('REJECT_LEAVE')")
    public ResponseEntity<?> rejectLeave(@PathVariable int id, Principal principal) {
        Optional<LeaveRequest> optionalRequest = leaveRequestRepo.findById(id);
        if (optionalRequest.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Leave request not found.");
        }
        LeaveRequest request = optionalRequest.get();

        // Check if the current user is the manager of the employee who submitted the request
        Users approver = userService.getUserByUsername(principal.getName());
        Employees employee = request.getEmployee();
        List<Employees> hierarchy = employeeRepo.findByEmployee(employee);

        boolean isManager = hierarchy.stream()
                .anyMatch(h -> h.getManager().equals(approver.getEmployee()));
        if (!isManager) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("You are not authorized to reject this leave request.");
        }

        request.setStatus(LeaveStatus.REJECTED);
        leaveRequestRepo.save(request);
        return ResponseEntity.ok("Leave request rejected.");
    }

    // Endpoint to list all leave requests (for admin/manager view)
    @GetMapping("/request-list")
    @PreAuthorize("hasAuthority('VIEW_ALL_REQUESTS')")
    public String requestList(Model model, Principal principal) {
        Users currentUser = userService.getUserByUsername(principal.getName());
        List<LeaveRequest> requests;

        // If the user has a role in a specific department, filter requests by that department
        Set<Roles> userRoles = currentUser.getRoles();
        boolean isAdmin = userRoles.stream().anyMatch(role -> role.getRname().equals("ROLE_ADMIN"));
        if (isAdmin) {
            requests = leaveRequestRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
        } else {
            // Filter requests by the department of the manager
            Departments department = userRoles.stream()
                    .filter(role -> role.getDepartment() != null)
                    .map(Roles::getDepartment)
                    .findFirst()
                    .orElse(null);
            if (department != null) {
                requests = leaveRequestRepo.findByEmployeeDepartment(department);
            } else {
                // If no department-specific role, show requests from direct reports
                List<Employees> directReports = employeeRepo.findByManager(currentUser.getEmployee());
                requests = leaveRequestRepo.findByEmployeeIn(directReports);
            }
        }

        model.addAttribute("requests", requests);
        return "leave/request-list";
    }
}
