package com.mycompany.LeaveManagementSystem.controller;


//import com.mycompany.LeaveManagementSystem.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

//    @Autowired
//    private LeaveRequestService service;

    @GetMapping("/")
    public String home() {
        return "index";
    }
    @GetMapping("/create")
    public String create() {
        return "create";
    }

//    @PostMapping("/create-request")
//    public String createRequest(LeaveRequest request, @RequestParam(required = false) Integer ownerId) {
//        service.saveRequest(request, ownerId);
//        return "redirect:/home";
//    }
}