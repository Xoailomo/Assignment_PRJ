package com.mycompany.LeaveManagementSystem.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @Column(length = 150)
    private String username;
    
    @Column(nullable = false, length = 150)
    private String password;
    
    @Column(nullable = false, length = 150)
    private String displayname;
    
    @ManyToOne
    @JoinColumn(name = "eid")
    private Employees employee;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }
    
    
}