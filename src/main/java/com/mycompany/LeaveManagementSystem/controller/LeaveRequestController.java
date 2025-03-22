/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.controller;

import com.mycompany.LeaveManagementSystem.model.Employee;
import com.mycompany.LeaveManagementSystem.model.LeaveRequest;
import com.mycompany.LeaveManagementSystem.model.LeaveStatus;
import com.mycompany.LeaveManagementSystem.repository.EmployeeRepository;
import com.mycompany.LeaveManagementSystem.repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 *
 * @author phank
 */
@Controller
@RequestMapping("/leave-requests")
public class LeaveRequestController {

    @Autowired
    private LeaveRequestRepository leaveRequestRepo;

    @Autowired
    private EmployeeRepository employeeRepo;

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("leaveRequest", new LeaveRequest());
        return "leave/create";
    }

    @PostMapping("/create")
    public String submitLeaveRequest(@ModelAttribute LeaveRequest leaveRequest, Principal principal) {
        Employee employee = employeeRepo.findByEmail(principal.getName());
        leaveRequest.setEmployee(employee);
        leaveRequest.setStatus(LeaveStatus.INPROGRESS);
        leaveRequestRepo.save(leaveRequest);
        return "redirect:/leave-requests/my-requests";
    }

    @GetMapping("/my-requests")
    public String myRequests(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        Employee employee = employeeRepo.findByEmail(principal.getName());
        if (employee == null) {
            return "error/403";
        }
        List<LeaveRequest> requests = leaveRequestRepo.findByEmployeeId(employee.getId());
        model.addAttribute("requests", requests);
        return "leave/my-requests";
    }

    @GetMapping("/request-list")
    public String requestList(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";  // Chuyển hướng nếu chưa đăng nhập
        }

        List<LeaveRequest> requests = leaveRequestRepo.findAll();
        model.addAttribute("requests", requests);

        return "leave/request-list"; // Trả về trang request-list.html
    }

    @GetMapping("/approve/{id}")
    public String approveLeave(@PathVariable Long id) {
        LeaveRequest request = leaveRequestRepo.findById(id).orElse(null);
        if (request != null) {
            request.setStatus(LeaveStatus.APPROVED);
            leaveRequestRepo.save(request);
        }
        return "redirect:/leave-requests/request-list";
    }

    @GetMapping("/reject/{id}")
    public String rejectLeave(@PathVariable Long id) {
        LeaveRequest request = leaveRequestRepo.findById(id).orElse(null);
        if (request != null) {
            request.setStatus(LeaveStatus.REJECTED);
            leaveRequestRepo.save(request);
        }
        return "redirect:/leave-requests/request-list";
    }

}
