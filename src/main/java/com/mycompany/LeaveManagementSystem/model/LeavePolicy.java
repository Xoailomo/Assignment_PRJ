/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author phank
 */
@Getter
@Setter
@Entity
@Table(name = "leave_policies")
public class LeavePolicy {
    @Id
    private int lpid;

    @Column(nullable = false, length = 150)
    private String policyName;

    @Column(nullable = false)
    private int leaveLockPeriod; // Months required before applying for leave

    @Column(nullable = false)
    private int leaveNoticePeriod; // Days required before applying for leave
}
