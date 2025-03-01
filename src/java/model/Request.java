/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Timestamp;
import java.sql.Date;
/**
 *
 * @author phank
 */
public class Request {
    private int requestId;
    private int userId;
    private Date fromDate;
    private Date toDate;
    private String reason;
    private String status;
    private Integer processedBy; // NULLable
    private Timestamp createdAt;
    private int createdBy;
    private User user; // Để lưu thông tin user

    // Getters and Setters
    public int getRequestId() { return requestId; }
    public void setRequestId(int requestId) { this.requestId = requestId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public Date getFromDate() { return fromDate; }
    public void setFromDate(Date fromDate) { this.fromDate = fromDate; }
    public Date getToDate() { return toDate; }
    public void setToDate(Date toDate) { this.toDate = toDate; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Integer getProcessedBy() { return processedBy; }
    public void setProcessedBy(Integer processedBy) { this.processedBy = processedBy; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
    public int getCreatedBy() { return createdBy; }
    public void setCreatedBy(int createdBy) { this.createdBy = createdBy; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}