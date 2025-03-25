/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.model;
import lombok.*;
import jakarta.persistence.*;
import java.util.Date;
/**
 *
 * @author phank
 */
@Getter
@Setter
@Entity
@Table(name = "holidays")
public class Holidays {
    @Id
    private int hid;

    @Column(nullable = false, length = 150)
    private String holidayName;

    @Column(nullable = false)
    private Date holidayDate;
}
