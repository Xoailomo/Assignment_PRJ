/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.repository;

import com.mycompany.LeaveManagementSystem.model.UserDepartmentRoleId;
import com.mycompany.LeaveManagementSystem.model.UserDepartmentRoles;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author phank
 */
@Repository
public interface UserDepartmentRoleRepository extends JpaRepository<UserDepartmentRoles, UserDepartmentRoleId> {

    @Query("SELECT r.rname FROM UserDepartmentRoles udr JOIN udr.role r WHERE udr.user.username = :username")
    List<String> findRolesByUsername(@Param("username") String username);
}
