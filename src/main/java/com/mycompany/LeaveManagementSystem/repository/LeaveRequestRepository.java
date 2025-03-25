/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.repository;

import com.mycompany.LeaveManagementSystem.model.Departments;
import com.mycompany.LeaveManagementSystem.model.Employees;
import com.mycompany.LeaveManagementSystem.model.LeaveRequest;
import com.mycompany.LeaveManagementSystem.model.LeaveTypes;
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

    List<LeaveRequest> findByEmployee(Employees employee);

    List<LeaveRequest> findByLeaveType(LeaveTypes leaveType);

    @Override
    Optional<LeaveRequest> findById(Integer id); // Sửa từ Long id thành Integer id

    List<LeaveRequest> findByEmployeeAndStartDate(Employees emp, LocalDate startDate);

    List<LeaveRequest> findByEmployeeAndEndDate(Employees emp, LocalDate endDate);

    List<LeaveRequest> findByEmployeeAndStartDateBeforeAndEndDateAfter(
            Employees employee, LocalDate endDate, LocalDate startDate);

    @Query("SELECT lr FROM LeaveRequest lr WHERE lr.employee IN :employees "
            + "AND lr.startDate <= :endDate AND lr.endDate >= :startDate")
    List<LeaveRequest> findByEmployeesAndDateRange(@Param("employees") List<Employees> employees,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    @Query("SELECT lr FROM LeaveRequest lr WHERE lr.employee = :employee "
            + "AND lr.startDate <= :endDate AND lr.endDate >= :startDate")
    List<LeaveRequest> findByEmployeeAndDateRange(@Param("employee") Employees employee,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    List<LeaveRequest> findByEmployeeDepartment(Departments department);
    List<LeaveRequest> findByEmployeeIn(List<Employees> employees);
}
