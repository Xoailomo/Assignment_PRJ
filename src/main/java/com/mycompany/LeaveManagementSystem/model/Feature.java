/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.LeaveManagementSystem.model;

/**
 *
 * @author phank
 */
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Features")
public class Feature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @OneToMany(mappedBy = "feature", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<RoleFeature> roleFeatures;

    // Getter và Setter cho id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter và Setter cho name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter và Setter cho roleFeatures
    public Set<RoleFeature> getRoleFeatures() {
        return roleFeatures;
    }

    public void setRoleFeatures(Set<RoleFeature> roleFeatures) {
        this.roleFeatures = roleFeatures;
    }
}
