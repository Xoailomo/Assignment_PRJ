/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.LeaveManagementSystem.controller;

/**
 *
 * @author phank
 */
import com.mycompany.LeaveManagementSystem.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RequestController {

    @Autowired
    private LeaveRequestService service;

    @GetMapping("/request-list")
    public String requestList(Model model) {
        model.addAttribute("requests", service.getAllRequests());
        return "request-list";
    }

    @GetMapping("/my-request")
    public String myRequest(Model model) {
        model.addAttribute("requests", service.getUserRequests());
        return "my-request";
    }
}