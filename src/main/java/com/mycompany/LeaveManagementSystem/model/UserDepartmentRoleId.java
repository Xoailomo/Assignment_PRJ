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
import java.io.Serializable;

public class UserDepartmentRoleId implements Serializable {
    private String username;
    private int did;  // ✅ Không đổi tên vì trong entity sẽ dùng department.getId()
    private int rid;
}



