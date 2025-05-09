package com.mycompany.LeaveManagementSystem.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_department_roles")
@IdClass(UserDepartmentRoleId.class)
public class UserDepartmentRoles {

    @Id
    @Column(name = "username")
    private String username;  // Matches UserDepartmentRoleId.username

    @Id
    @Column(name = "did")
    private int did;  // Matches UserDepartmentRoleId.did

    @Id
    @Column(name = "rid")
    private int rid;  // Matches UserDepartmentRoleId.rid

    @ManyToOne
    @JoinColumn(name = "username", insertable = false, updatable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "did", insertable = false, updatable = false)
    private Departments department;

    @ManyToOne
    @JoinColumn(name = "rid", insertable = false, updatable = false)
    private Roles role;
}