/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author phank
 */
@Getter
@Setter
public class EmployeeLeaveEntitlementId implements Serializable {
    private int eid;  // Employee ID
    private int ltid; // Leave Type ID
}

