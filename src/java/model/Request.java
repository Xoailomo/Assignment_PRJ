/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Date;
/**
 *
 * @author phank
 */
public class Request {
    private int id;
    private int userId;
    private Date fromDate;
    private Date toDate;
    private String reason;
    private String status;
    private int processedBy;

//    public Request(int id, int userId, Date fromDate, Date toDate, String reason, String status, int processedBy) {
//        this.id = id;
//        this.userId = userId;
//        this.fromDate = fromDate;
//        this.toDate = toDate;
//        this.reason = reason;
//        this.status = status;
//        this.processedBy = processedBy;
//    }

    // Getters và setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
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
    public int getProcessedBy() { return processedBy; }
    public void setProcessedBy(int processedBy) { this.processedBy = processedBy; }
}
