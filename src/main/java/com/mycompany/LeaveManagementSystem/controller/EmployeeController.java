/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.controller;


import com.mycompany.LeaveManagementSystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author phank
 */
@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepo;

    @GetMapping({"","/"})
    public String getEmployee(Model model) {
        var employees = employeeRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("employees", employees);
        return "employees/index"; // Giao diện danh sách nhân viên
    }

//    @GetMapping("/add")
//    public String showAddForm(Model model) {
//        model.addAttribute("employee", new Employees());
//        return "employees/add"; // Trang thêm nhân viên
//    }
//    @PostMapping("/add")
//    public String addEmployee(@ModelAttribute Employees employee, RedirectAttributes redirectAttributes) {
//        employeeRepo.save(employee);
//        redirectAttributes.addFlashAttribute("success", "Employees added successfully!");
//        return "redirect:/employees/";
//    }

//    @GetMapping("/new")
//    public String showCreateForm(Model model) {
//        model.addAttribute("employee", new Employees());
//        return "employees/form"; // Giao diện form thêm nhân viên
//    }

//    @PostMapping("/save")
//    public String saveEmployee(@ModelAttribute Employees employee) {
//        employee.setCreatedAt(LocalDate.now());
//        employeeRepo.save(employee);
//        return "redirect:/employees";
//    }
//
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable int id, RedirectAttributes redirectAttributes) {
        employeeRepo.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Employee deleted successfully!");
        return "redirect:/employees/";
    }
}

