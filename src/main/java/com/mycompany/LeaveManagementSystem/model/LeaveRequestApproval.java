/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.LeaveManagementSystem.model;

/**
 *
 * @author phank
 */

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "LeaveRequestApprovals")
public class LeaveRequestApproval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leaveRequestId", nullable = false)
    private LeaveRequest leaveRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approverId", nullable = false)
    private User approver;

    @Column(name = "approved", nullable = false)
    private Boolean approved;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    // Getter và Setter cho id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter và Setter cho leaveRequest
    public LeaveRequest getLeaveRequest() {
        return leaveRequest;
    }

    public void setLeaveRequest(LeaveRequest leaveRequest) {
        this.leaveRequest = leaveRequest;
    }

    // Getter và Setter cho approver
    public User getApprover() {
        return approver;
    }

    public void setApprover(User approver) {
        this.approver = approver;
    }

    // Getter và Setter cho approved
    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    // Getter và Setter cho status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Getter và Setter cho date
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    // Getter và Setter cho comment
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
