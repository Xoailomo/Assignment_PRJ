/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

/**
 *
 * @author phank
 */
@Getter
@Setter
@Entity
@Table(name = "leave_requests")
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int elid;  // Mã đơn nghỉ

    @ManyToOne
    @JoinColumn(name = "eid", nullable = false)
    private Employees employee;  // Nhân viên nghỉ phép

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)  
    private Employees createdBy;  // Người tạo đơn (có thể là quản lý)

    @ManyToOne
    @JoinColumn(name = "processed_by")  
    private Employees processedBy;  // Người duyệt đơn nghỉ (Manager/Admin)

    @ManyToOne
    @JoinColumn(name = "ltid", nullable = false)
    private LeaveTypes leaveType;  // Loại nghỉ phép

    @Column(nullable = false, length = 255)
    private String reasonTitle;  // Tiêu đề lý do nghỉ

    @Column(nullable = false)
    private LocalDate  startDate;  // Ngày bắt đầu nghỉ

    @Column(nullable = false)
    private LocalDate  endDate;  // Ngày kết thúc nghỉ

    @Enumerated(EnumType.STRING) // Lưu enum dạng chuỗi trong database
    private LeaveStatus status; 
    
    private String reason;
    
}
