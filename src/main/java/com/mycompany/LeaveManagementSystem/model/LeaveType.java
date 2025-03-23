/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.model;

/**
 *
 * @author phank
 */
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum LeaveType {
      VACATION, SICK, MATERNITYLEAVE, UNPAIDLEAVE, OTHER;

//    @JsonCreator
//    public static LeaveType fromString(String value) {
//        return LeaveType.valueOf(value.toUpperCase()); // Chuyển thành Enum
//    }
//
//    @JsonValue
//    public String toJson() {
//        return name(); // Trả về String khi serialize
//    }
}
