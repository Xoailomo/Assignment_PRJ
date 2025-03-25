package com.mycompany.LeaveManagementSystem.model;

import jakarta.persistence.*;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
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

    @Column(nullable = false, length = 150, unique = true)
    private String email;
    
    private Date createAt;

    @ManyToOne
    @JoinColumn(name = "eid")
    private Employees employee;

    @ManyToMany
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "userid"),
        inverseJoinColumns = @JoinColumn(name = "roleid")
    )
    private Set<Roles> roles;

    @ManyToMany
    @JoinTable(
        name = "user_features",
        joinColumns = @JoinColumn(name = "userid"),
        inverseJoinColumns = @JoinColumn(name = "featureid")
    )
    private Set<Features> features;
}