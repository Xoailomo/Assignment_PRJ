package com.mycompany.LeaveManagementSystem.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Roles {
   @Id
    private int rid;
    
    @Column(nullable = false, length = 150)
    private String rname;
    
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Departments department; // Role can be department-specific
}
