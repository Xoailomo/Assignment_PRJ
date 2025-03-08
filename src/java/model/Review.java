/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author phank
 */
public class Review {
    public int reviewId;
    public int leaveId;
    public String reviewType;
    public String dateOfApproval;

    public Review(int reviewId, int leaveId, String reviewType, String dateOfApproval) {
        this.reviewId = reviewId;
        this.leaveId = leaveId;
        this.reviewType = reviewType;
        this.dateOfApproval = dateOfApproval;
    }
    
}
