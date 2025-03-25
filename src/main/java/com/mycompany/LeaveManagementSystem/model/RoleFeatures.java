/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.model;


import jakarta.persistence.*;

/**
 *
 * @author phank
 */
@Entity
@Table(name="role_features")
public class RoleFeatures {
    @Id
    @ManyToOne
    @JoinColumn(name="rid", nullable = false)
    private Roles role;
    
    @Id
    @ManyToOne
    @JoinColumn(name="fid", nullable = false)
    private Features feature;

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public Features getFeature() {
        return feature;
    }

    public void setFeature(Features feature) {
        this.feature = feature;
    }
    
    
}
