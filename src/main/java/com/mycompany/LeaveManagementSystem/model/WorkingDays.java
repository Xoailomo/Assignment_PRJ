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
@Table(name = "working_days")
public class WorkingDays {
    @Id
    private int wdid;

    @Column(nullable = false, length = 50)
    private String dayOfWeek;

    @Column(nullable = false)
    private boolean isWorkingDay;
}
