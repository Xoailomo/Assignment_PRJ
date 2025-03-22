package com.mycompany.LeaveManagementSystem.service;

import com.mycompany.LeaveManagementSystem.model.LeaveRequest;
import com.mycompany.LeaveManagementSystem.model.LeaveType;
import com.mycompany.LeaveManagementSystem.model.User;
import com.mycompany.LeaveManagementSystem.repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LeaveRequestService {

    @Autowired
    private LeaveRequestRepository repository;

    @Autowired
    private UserService userService;

    /**
     * Lưu yêu cầu nghỉ phép
     */
    public void saveRequest(LeaveRequest request, Integer ownerId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User createdBy = userService.findByUsername(username);

        if (createdBy == null) {
            throw new IllegalArgumentException("User not found: " + username);
        }

        request.setCreatedBy(createdBy);

        // Nếu ownerId không được cung cấp, gán owner là chính người tạo
        if (ownerId == null) {
            request.setOwner(createdBy);
        } else {
            User owner = userService.findById(ownerId);
            if (owner == null) {
                throw new IllegalArgumentException("Owner not found with ID: " + ownerId);
            }
            request.setOwner(owner);
        }

        request.setStatus("Pending");
        request.setCreatedAt(LocalDateTime.now());
        request.setRequestType("Full Day");

        // Giả định loại nghỉ phép mặc định là ID = 1
        LeaveType defaultLeaveType = new LeaveType();
        defaultLeaveType.setId(1);
        request.setLeaveType(defaultLeaveType);

        repository.save(request);
    }

    /**
     * Lấy danh sách yêu cầu nghỉ phép của người dùng hiện tại
     */
    public List<LeaveRequest> getUserRequests() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);

        if (user == null) {
            throw new IllegalArgumentException("User not found: " + username);
        }

        return repository.findByOwnerId(user.getId());
    }

    /**
     * Lấy toàn bộ yêu cầu nghỉ phép
     */
    public List<LeaveRequest> getAllRequests() {
        return repository.findAll();
    }
}
