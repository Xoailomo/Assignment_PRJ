/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.repository;

/**
 *
 * @author phank
 */
import com.mycompany.LeaveManagementSystem.model.LeaveRequest;
import com.mycompany.LeaveManagementSystem.model.LeaveType;
import com.mycompany.LeaveManagementSystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class LeaveRequestRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(LeaveRequest request) {
        String sql = "INSERT INTO LeaveRequests (ltId, reason, startDate, endDate, requestType, status, createdAt, createdBy, ownerId, processBy) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                request.getLeaveType().getId(), // Lấy ID từ LeaveType
                request.getReason(),
                request.getStartDate(),
                request.getEndDate(),
                request.getRequestType(),
                request.getStatus(),
                request.getCreatedAt(),
                request.getCreatedBy().getId(), // Lấy ID từ User createdBy
                request.getOwner().getId(), // Lấy ID từ User owner
                request.getProcessBy() != null ? request.getProcessBy().getId() : null // Nếu processBy null thì lưu null
        );
    }

    /**
     * Tìm tất cả đơn nghỉ theo chủ sở hữu (employee).
     */
    public List<LeaveRequest> findByOwnerId(int ownerId) {
        String sql = "SELECT * FROM LeaveRequests WHERE ownerId = ?";
        return jdbcTemplate.query(sql, this::mapRowToLeaveRequest, ownerId);
    }

    /**
     * Lấy toàn bộ đơn nghỉ.
     */
    public List<LeaveRequest> findAll() {
        String sql = "SELECT * FROM LeaveRequests";
        return jdbcTemplate.query(sql, this::mapRowToLeaveRequest);
    }

    /**
     * Chuyển đổi dữ liệu từ ResultSet sang LeaveRequest.
     */
    private LeaveRequest mapRowToLeaveRequest(ResultSet rs, int rowNum) throws SQLException {
        LeaveRequest request = new LeaveRequest();
        request.setId(rs.getInt("id"));

        // Ánh xạ LeaveType từ ltId
        LeaveType leaveType = new LeaveType();
        leaveType.setId(rs.getInt("ltId"));
        request.setLeaveType(leaveType);

        request.setReason(rs.getString("reason"));
        request.setStartDate(rs.getDate("startDate").toLocalDate());
        request.setEndDate(rs.getDate("endDate").toLocalDate());
        request.setRequestType(rs.getString("requestType"));
        request.setStatus(rs.getString("status"));
        request.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());

        // Ánh xạ User (createdBy)
        User createdBy = new User();
        createdBy.setId(rs.getInt("createdBy"));
        request.setCreatedBy(createdBy);

        // Ánh xạ User (owner)
        User owner = new User();
        owner.setId(rs.getInt("ownerId"));
        request.setOwner(owner);

        // Ánh xạ User (processBy) - kiểm tra nếu NULL
        int processById = rs.getInt("processBy");
        if (!rs.wasNull()) {
            User processBy = new User();
            processBy.setId(processById);
            request.setProcessBy(processBy);
        } else {
            request.setProcessBy(null);
        }

        return request;
    }
}
