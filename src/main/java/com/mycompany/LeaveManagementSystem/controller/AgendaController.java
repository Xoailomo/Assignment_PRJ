/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.LeaveManagementSystem.controller;

/**
 *
 * @author phank
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AgendaController {

    @GetMapping("/company-agenda")
    public String companyAgenda() {
        return "company-agenda";
    }

    @GetMapping("/my-agenda")
    public String myAgenda() {
        return "my-agenda";
    }
}
