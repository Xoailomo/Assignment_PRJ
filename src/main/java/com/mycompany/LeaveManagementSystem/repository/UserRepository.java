/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.repository;

/**
 *
 * @author phank
 */
import com.mycompany.LeaveManagementSystem.model.Employee;
import com.mycompany.LeaveManagementSystem.model.Role;
import com.mycompany.LeaveManagementSystem.model.User;
import com.mycompany.LeaveManagementSystem.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public class UserRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User findByEmail(String email) {
        String sql = "SELECT * FROM Users WHERE email = ?";
        try {
            return jdbcTemplate.queryForObject(sql, this::mapRowToUser, email);
        } catch (Exception e) {
            return null;
        }
    }

    public User findByUsername(String username) {
        String sql = "SELECT \n"
                + "    u.id, \n"
                + "    u.username, \n"
                + "    u.password, \n"
                + "    u.displayName, \n"
                + "    u.email, \n"
                + "    u.eid, \n"
                + "    STRING_AGG(r.name, ', ') AS roles  -- Aggregate function for roles\n"
                + "FROM Users u\n"
                + "JOIN UserRole ur ON u.id = ur.userid\n"
                + "JOIN Roles r ON ur.roleid = r.id\n"
                + "WHERE u.username = ? \n"
                + "GROUP BY u.id, u.username, u.password, u.displayName, u.email, u.eid;";
        try {
            return jdbcTemplate.queryForObject(sql, this::mapRowToUser, username);
        } catch (Exception e) {
            return null;
        }
    }

    public Optional<User> findById(int id) {
        String sql = "SELECT * FROM Users WHERE id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, this::mapRowToUser, id));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public void save(User user) {
        String sql = "INSERT INTO Users (username, password, displayName, email, eid) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getDisplayName(), user.getEmail(), user.getEmployee().getId());
    }

    public void saveResetToken(int userId, String token, String expiryDate) {
        String sql = "INSERT INTO PasswordResetTokens (userId, token, expiryDate) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, userId, token, expiryDate);
    }

    private User mapRowToUser(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();

        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setDisplayName(rs.getString("displayName"));
        user.setEmail(rs.getString("email"));

        // Ánh xạ Employee từ employeeId (eid)
        int employeeId = rs.getInt("eid");
        if (!rs.wasNull()) {
            Employee employee = new Employee();
            employee.setId(employeeId);
            user.setEmployee(employee);
        }

        // Lấy danh sách roles từ bảng UserRole
        Set<UserRole> userRoles = new HashSet<>();
        int roleId = rs.getInt("role_id");
        String roleName = rs.getString("role_name");

        if (!rs.wasNull() && roleName != null) {
            Role role = new Role();
            role.setId(roleId);
            role.setName(roleName);

            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);

            userRoles.add(userRole);
        }

        user.setUserRoles(userRoles);
        return user;

    }
}
