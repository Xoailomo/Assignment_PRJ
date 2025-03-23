/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author phank
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @GetMapping("")
    public String test(Model model) {
        return "schedule"; // Đảm bảo file HTML này tồn tại trong templates/
    }
}
