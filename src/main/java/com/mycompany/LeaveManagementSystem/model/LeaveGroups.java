/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.model;
import lombok.*;
import jakarta.persistence.*;
/**
 *
 * @author phank
 */
@Getter
@Setter
@Entity
@Table(name = "leave_groups")
public class LeaveGroups {
     @Id
    private int lgid;

    @Column(nullable = false, length = 150)
    private String lgname;
}
