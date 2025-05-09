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
@Getter 
@Setter
@Entity
@Table(name="role_features")
public class RoleFeatures {
    @Id
    @ManyToOne
    @JoinColumn(name = "rid", nullable = false)
    private Roles role;

    @Id
    @ManyToOne
    @JoinColumn(name = "fid", nullable = false)
    private Features feature;
    
    
    
}
