/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.model;

import jakarta.persistence.*;
import lombok.*;

/**
 *
 * @author phank
 */
@Entity
@Table(name = "user_department_roles")
@IdClass(UserDepartmentRoleId.class) // ✅ Sử dụng @IdClass
public class UserDepartmentRoles {

    @Id
    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private Users user;

    @Id
    @ManyToOne
    @JoinColumn(name = "did", nullable = false)
    private Departments department;  // ✅ Đúng với entity

    @Id
    @ManyToOne
    @JoinColumn(name = "rid", nullable = false)
    private Roles role;    
    
}
