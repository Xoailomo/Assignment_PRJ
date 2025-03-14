/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author phank
 */
public class LeaveBalances {
    private LeaveDetail vacation;
    private LeaveDetail sickLeave;
    private LeaveDetail unpaidLeave;

    public LeaveDetail getVacation() {
        return vacation;
    }

    public void setVacation(LeaveDetail vacation) {
        this.vacation = vacation;
    }

    public LeaveDetail getSickLeave() {
        return sickLeave;
    }

    public void setSickLeave(LeaveDetail sickLeave) {
        this.sickLeave = sickLeave;
    }

    public LeaveDetail getUnpaidLeave() {
        return unpaidLeave;
    }

    public void setUnpaidLeave(LeaveDetail unpaidLeave) {
        this.unpaidLeave = unpaidLeave;
    }
    
}
