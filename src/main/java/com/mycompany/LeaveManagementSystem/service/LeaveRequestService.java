/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.service;

/**
 *
 * @author phank
 */
import com.mycompany.LeaveManagementSystem.model.LeaveRequest;
import java.util.List;

public interface LeaveRequestService {
    LeaveRequest createLeaveRequest(LeaveRequest leaveRequest);
    List<LeaveRequest> getAllLeaveRequests();
}

