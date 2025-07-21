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
@Table(name = "leaveGroup_Mappings")
public class LeaveGroupMappings {
    @EmbeddedId
    private LeaveGroupMappingId id;

    @ManyToOne
    @MapsId("lgid")
    @JoinColumn(name = "lgid", nullable = false)
    private LeaveGroups leaveGroup;

    @ManyToOne
    @MapsId("eid")
    @JoinColumn(name = "eid", nullable = false)
    private Employees employee;
}
