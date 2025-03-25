package com.mycompany.LeaveManagementSystem.controller;


//import com.mycompany.LeaveManagementSystem.service.LeaveRequestService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/admin/home")
    public String getAdminHome() {
        return "Admin Home Page";
    }
    @GetMapping("/manager/home")
    public String getManagerHome() {
        return "Manager Home Page";
    }
    @GetMapping("/staff/home")
    public String getStaffHome() {
        return "Staff Home Page";
    }
    @GetMapping("/create-request")
    public String create() {
        return "create";
    }

}