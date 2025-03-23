/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.repository;

import com.mycompany.LeaveManagementSystem.model.Employee;
import com.mycompany.LeaveManagementSystem.model.LeaveRequest;
import com.mycompany.LeaveManagementSystem.model.LeaveStatus;
import com.mycompany.LeaveManagementSystem.model.LeaveType;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author phank
 */
@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Integer> {

    List<LeaveRequest> findByEmployee(Employee employee);

    // Tìm tất cả yêu cầu nghỉ phép theo trạng thái (APPROVED, REJECTED, INPROGRESS)
    List<LeaveRequest> findByStatus(LeaveStatus status);

    // Tìm tất cả yêu cầu nghỉ phép của một nhân viên với trạng thái cụ thể
    List<LeaveRequest> findByEmployeeAndStatus(Employee employee, LeaveStatus status);

    List<LeaveRequest> findByLeaveType(LeaveType leaveType);

    @Override
    Optional<LeaveRequest> findById(Integer id); // Sửa từ Long id thành Integer id

    List<LeaveRequest> findByEmployeeAndStartDate(Employee emp, LocalDate startDate);

    List<LeaveRequest> findByEmployeeAndEndDate(Employee emp, LocalDate endDate);

    List<LeaveRequest> findByEmployeeAndStartDateBeforeAndEndDateAfter(
            Employee employee, LocalDate endDate, LocalDate startDate);

    @Query("SELECT lr FROM LeaveRequest lr WHERE lr.employee = :employee "
            + "AND lr.startDate <= :endDate AND lr.endDate >= :startDate")
    List<LeaveRequest> findByEmployeeAndDateRange(@Param("employee") Employee employee,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    // ✅ Thêm phương thức kiểm tra nếu một ngày cụ thể nằm trong khoảng nghỉ của nhân viên
    @Query("SELECT lr FROM LeaveRequest lr WHERE lr.employee = :employee "
            + "AND :date BETWEEN lr.startDate AND lr.endDate")
    List<LeaveRequest> findByEmployeeAndDate(@Param("employee") Employee emp,
            @Param("date") LocalDate date);

}
