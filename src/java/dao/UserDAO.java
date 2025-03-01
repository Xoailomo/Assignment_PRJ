package dao;

import dal.DBContext;
import model.User;
import model.Role;
import model.Department;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object for User-related operations
 */
public class UserDAO extends DBContext {

    public UserDAO() {
        super(); // Call DBContext constructor for database connection
    }

    /**
     * Authenticate a user by email and password
     * @param email User's email
     * @param password User's password (hashed in application)
     * @return User object if authenticated, null otherwise
     */
    public User getUserByEmailAndPassword(String email, String password) {
        String sql = "SELECT u.userId, u.userName, u.userEmail, u.userPassword, u.deptId, u.managerId, " +
                     "d.deptName " +
                     "FROM Users u " +
                     "JOIN Department d ON u.deptId = d.deptId " +
                     "WHERE u.userEmail = ? AND u.userPassword = ?";
        User user = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password); // Note: Password should be hashed in the application, not plain text
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("userId"));
                user.setUserName(rs.getString("userName")); // Sử dụng setUserName thay vì setName
                user.setEmail(rs.getString("userEmail"));
                user.setPassword(rs.getString("userPassword")); // Should be hashed
                Department dept = new Department();
                dept.setId(rs.getInt("deptId"));
                dept.setName(rs.getString("deptName"));
                user.setDepartment(dept);
                user.setManagerId(rs.getInt("managerId")); // Sử dụng setManagerId
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return user;
    }

    /**
     * Check if a user has a specific role
     * @param userId User ID
     * @param roleName Role name to check
     * @return boolean indicating if user has the role
     */
    public boolean hasRole(int userId, String roleName) {
        String sql = "SELECT COUNT(*) FROM UserRole ur " +
                     "JOIN Roles r ON ur.roleId = r.roleId " +
                     "WHERE ur.userId = ? AND r.roleName = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, roleName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Get all subordinates of a manager using recursive CTE
     * @param managerId Manager's userId
     * @return List of User objects representing subordinates
     */
    public List<User> getSubordinates(int managerId) {
        List<User> subordinates = new ArrayList<>();
        String sql = "WITH EmployeeTree AS (" +
                     "    SELECT userId, userName, userEmail, deptId, managerId " +
                     "    FROM Users " +
                     "    WHERE userId = ? " +
                     "    UNION ALL " +
                     "    SELECT u.userId, u.userName, u.userEmail, u.deptId, u.managerId " +
                     "    FROM Users u " +
                     "    INNER JOIN EmployeeTree et ON u.managerId = et.userId) " +
                     "SELECT u.userId, u.userName, u.userEmail, u.deptId, u.managerId, " +
                     "d.deptName " +
                     "FROM EmployeeTree u " +
                     "JOIN Department d ON u.deptId = d.deptId " +
                     "WHERE u.userId != ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, managerId);
            stmt.setInt(2, managerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("userId"));
                user.setUserName(rs.getString("userName")); // Sử dụng setUserName thay vì setName
                user.setEmail(rs.getString("userEmail"));
                Department dept = new Department();
                dept.setId(rs.getInt("deptId"));
                dept.setName(rs.getString("deptName"));
                user.setDepartment(dept);
                user.setManagerId(rs.getInt("managerId")); // Sử dụng setManagerId
                subordinates.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return subordinates;
    }

    /**
     * Get a user by ID
     * @param userId User ID
     * @return User object or null if not found
     */
    public User getUserById(int userId) {
        String sql = "SELECT u.userId, u.userName, u.userEmail, u.userPassword, u.deptId, u.managerId, " +
                     "d.deptName " +
                     "FROM Users u " +
                     "JOIN Department d ON u.deptId = d.deptId " +
                     "WHERE u.userId = ?";
        User user = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("userId"));
                user.setUserName(rs.getString("userName")); // Sử dụng setUserName thay vì setName
                user.setEmail(rs.getString("userEmail"));
                user.setPassword(rs.getString("userPassword"));
                Department dept = new Department();
                dept.setId(rs.getInt("deptId"));
                dept.setName(rs.getString("deptName"));
                user.setDepartment(dept);
                user.setManagerId(rs.getInt("managerId")); // Sử dụng setManagerId
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return user;
    }

    /**
     * Get all roles for a user (returns roles separately, not stored in User)
     * @param userId User ID
     * @return List of Role objects
     */
    public List<Role> getRolesByUserId(int userId) {
        List<Role> roles = new ArrayList<>();
        String sql = "SELECT r.roleId, r.roleName, r.roleDescription " +
                     "FROM UserRole ur " +
                     "JOIN Roles r ON ur.roleId = r.roleId " +
                     "WHERE ur.userId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("roleId"));
                role.setName(rs.getString("roleName"));
                role.setDescription(rs.getString("roleDescription"));
                roles.add(role);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return roles;
    }

    @Override
    public ArrayList list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Object model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Object model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Object model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}