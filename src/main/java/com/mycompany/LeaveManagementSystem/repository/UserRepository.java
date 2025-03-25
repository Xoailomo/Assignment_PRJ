package com.mycompany.LeaveManagementSystem.repository;

import com.mycompany.LeaveManagementSystem.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, String> {

    Optional<Users> findByUsername(String username);

    Optional<Users> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    // Lấy danh sách roles của user từ bảng UserDepartmentRole
    @Query("SELECT r.rname FROM user_department_roles udr JOIN udr.role r WHERE udr.user.username = :username")
    List<String> findRolesByUsername(@Param("username") String username);
}
