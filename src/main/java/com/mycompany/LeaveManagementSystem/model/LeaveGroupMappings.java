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
@Table(name = "LeaveGroupMappings")
@IdClass(LeaveGroupMappingId.class)
public class LeaveGroupMappings {
    @Id
    @ManyToOne
    @JoinColumn(name = "lgid", nullable = false)
    private LeaveGroups leaveGroup;

    @Id
    @ManyToOne
    @JoinColumn(name = "eid", nullable = false)
    private Employees employee;
}
