/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.model;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

/**
 *
 * @author phank
 */
@Getter
@Setter
@Entity
@Table(name = "employees")
public class Employees {
    @Id
    private int eid;
    
    @Column(nullable = false, length = 150)
    private String ename;
    
    @ManyToOne
    @JoinColumn(name = "managerid")
    private Employees manager;
    
    @ManyToOne
    @JoinColumn(name = "did", nullable = false)
    private Departments department;

    
    
}