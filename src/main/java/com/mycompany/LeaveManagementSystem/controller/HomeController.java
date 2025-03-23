package com.mycompany.LeaveManagementSystem.controller;


//import com.mycompany.LeaveManagementSystem.service.LeaveRequestService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

//    @Autowired
//    private LeaveRequestService service;

    @GetMapping("/")
    public String home() {
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
    

//    @PostMapping("/create-request")
//    public String createRequest(LeaveRequest request, @RequestParam(required = false) Integer ownerId) {
//        service.saveRequest(request, ownerId);
//        return "redirect:/home";
//    }
}