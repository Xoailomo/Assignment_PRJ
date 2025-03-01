package dao;

import dal.DBContext;
import model.Request;
import model.User;
import model.Department;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object for Request-related operations
 */
public class RequestDAO extends DBContext {

    public RequestDAO() {
        super(); // Call DBContext constructor for database connection
    }

    /**
     * Create a new leave request
     * @param request The Request object to create
     */
    public void createRequest(Request request) {
        String sql = "INSERT INTO Request (userId, fromDate, toDate, requestReason, requestStatus, createdBy) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, request.getUserId());
            stmt.setDate(2, new java.sql.Date(request.getFromDate().getTime()));
            stmt.setDate(3, new java.sql.Date(request.getToDate().getTime()));
            stmt.setString(4, request.getReason());
            stmt.setString(5, request.getStatus());
            stmt.setInt(6, request.getCreatedBy());
            stmt.executeUpdate();

            // Get the generated requestId
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                request.setRequestId(rs.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RequestDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RequestDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Get all requests for a specific user
     * @param userId User ID
     * @return List of Request objects
     */
    public List<Request> getRequestsByUser(int userId) {
        List<Request> requests = new ArrayList<>();
        String sql = "SELECT r.requestId, r.userId, r.fromDate, r.toDate, r.requestReason, r.requestStatus, " +
                     "r.processedBy, r.createdAt, r.createdBy, " +
                     "u.userName, u.userEmail, d.deptName " +
                     "FROM Request r " +
                     "JOIN Users u ON r.userId = u.userId " +
                     "JOIN Department d ON u.deptId = d.deptId " +
                     "WHERE r.userId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Request request = new Request();
                request.setRequestId(rs.getInt("requestId"));
                request.setUserId(rs.getInt("userId"));
                request.setFromDate(rs.getDate("fromDate"));
                request.setToDate(rs.getDate("toDate"));
                request.setReason(rs.getString("requestReason"));
                request.setStatus(rs.getString("requestStatus"));
                request.setProcessedBy(rs.getInt("processedBy")); // Could be null
                request.setCreatedAt(rs.getTimestamp("createdAt"));
                request.setCreatedBy(rs.getInt("createdBy"));

                User user = new User();
                user.setId(rs.getInt("userId"));
                user.setUserName(rs.getString("userName")); // Sử dụng setUserName
                user.setEmail(rs.getString("userEmail"));
                Department dept = new Department();
                dept.setId(rs.getInt("deptId")); // Giả sử Department có getDeptId()
                dept.setName(rs.getString("deptName"));
                user.setDepartment(dept);
                request.setUser(user); // Giả sử Request có phương thức setUser()

                requests.add(request);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RequestDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RequestDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return requests;
    }

    /**
     * Get pending requests for a manager's subordinates
     * @param managerId Manager's userId
     * @return List of Request objects for pending requests of subordinates
     */
    public List<Request> getPendingRequestsForManager(int managerId) {
        List<Request> requests = new ArrayList<>();
        String sql = "WITH EmployeeTree AS (" +
                     "    SELECT userId FROM Users WHERE userId = ? " +
                     "    UNION ALL " +
                     "    SELECT u.userId FROM Users u " +
                     "    INNER JOIN EmployeeTree et ON u.managerId = et.userId) " +
                     "SELECT r.requestId, r.userId, r.fromDate, r.toDate, r.requestReason, r.requestStatus, " +
                     "r.processedBy, r.createdAt, r.createdBy, " +
                     "u.userName, u.userEmail, d.deptName " +
                     "FROM Request r " +
                     "JOIN Users u ON r.userId = u.userId " +
                     "JOIN Department d ON u.deptId = d.deptId " +
                     "JOIN EmployeeTree et ON r.userId = et.userId " +
                     "WHERE r.requestStatus = 'Inprogress'";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, managerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Request request = new Request();
                request.setRequestId(rs.getInt("requestId"));
                request.setUserId(rs.getInt("userId"));
                request.setFromDate(rs.getDate("fromDate"));
                request.setToDate(rs.getDate("toDate"));
                request.setReason(rs.getString("requestReason"));
                request.setStatus(rs.getString("requestStatus"));
                request.setProcessedBy(rs.getInt("processedBy")); // Could be null
                request.setCreatedAt(rs.getTimestamp("createdAt"));
                request.setCreatedBy(rs.getInt("createdBy"));

                User user = new User();
                user.setId(rs.getInt("userId"));
                user.setUserName(rs.getString("userName")); // Sử dụng setUserName
                user.setEmail(rs.getString("userEmail"));
                Department dept = new Department();
                dept.setId(rs.getInt("deptId")); // Giả sử Department có getDeptId()
                dept.setName(rs.getString("deptName"));
                user.setDepartment(dept);
                request.setUser(user); // Giả sử Request có phương thức setUser()

                requests.add(request);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RequestDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RequestDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return requests;
    }

    /**
     * Update the status of a request
     * @param requestId Request ID
     * @param status New status ('Approved' or 'Rejected')
     * @param processedBy User ID of the person processing the request
     */
    public void updateRequestStatus(int requestId, String status, int processedBy) {
        String sql = "UPDATE Request SET requestStatus = ?, processedBy = ? WHERE requestId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, processedBy);
            stmt.setInt(3, requestId);
            stmt.executeUpdate();

            // Log the action in Request_History
            String historySql = "INSERT INTO Request_History (requestId, action, processedBy, processedAt) " +
                               "VALUES (?, ?, ?, GETDATE())";
            try (PreparedStatement historyStmt = connection.prepareStatement(historySql)) {
                historyStmt.setInt(1, requestId);
                historyStmt.setString(2, status);
                historyStmt.setInt(3, processedBy);
                historyStmt.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(RequestDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RequestDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Get approved requests for a department in a specific date range (for Agenda)
     * @param deptId Department ID
     * @param fromDate Start date
     * @param toDate End date
     * @return List of Request objects
     */
    public List<Request> getApprovedRequestsByDepartment(int deptId, String fromDate, String toDate) {
        List<Request> requests = new ArrayList<>();
        String sql = "SELECT r.requestId, r.userId, r.fromDate, r.toDate, r.requestReason, r.requestStatus, " +
                     "r.processedBy, r.createdAt, r.createdBy, " +
                     "u.userName, u.userEmail, d.deptName " +
                     "FROM Request r " +
                     "JOIN Users u ON r.userId = u.userId " +
                     "JOIN Department d ON u.deptId = d.deptId " +
                     "WHERE u.deptId = ? AND r.requestStatus = 'Approved' " +
                     "AND r.fromDate <= ? AND r.toDate >= ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, deptId);
            stmt.setDate(2, java.sql.Date.valueOf(toDate)); // Convert String to Date
            stmt.setDate(3, java.sql.Date.valueOf(fromDate)); // Convert String to Date
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Request request = new Request();
                request.setRequestId(rs.getInt("requestId"));
                request.setUserId(rs.getInt("userId"));
                request.setFromDate(rs.getDate("fromDate"));
                request.setToDate(rs.getDate("toDate"));
                request.setReason(rs.getString("requestReason"));
                request.setStatus(rs.getString("requestStatus"));
                request.setProcessedBy(rs.getInt("processedBy")); // Could be null
                request.setCreatedAt(rs.getTimestamp("createdAt"));
                request.setCreatedBy(rs.getInt("createdBy"));

                User user = new User();
                user.setId(rs.getInt("userId"));
                user.setUserName(rs.getString("userName")); // Sử dụng setUserName
                user.setEmail(rs.getString("userEmail"));
                Department dept = new Department();
                dept.setId(rs.getInt("deptId")); // Giả sử Department có getDeptId()
                dept.setName(rs.getString("deptName"));
                user.setDepartment(dept);
                request.setUser(user); // Giả sử Request có phương thức setUser()

                requests.add(request);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RequestDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RequestDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return requests;
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