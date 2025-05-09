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
@Table(name = "leave_types")
public class LeaveTypes {
    @Id
    private int ltid;

    @Column(nullable = false, length = 150)
    private String ltname;

    @Column(nullable = false)
    private boolean accrueEnabled; //Nghỉ phép có thể được tích lũy

    @Column(nullable = false)
    private boolean carryForward; // Leave can be carried forward
}
