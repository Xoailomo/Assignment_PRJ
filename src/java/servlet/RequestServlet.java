package servlet;

import dao.RequestDAO;
import dao.UserDAO;
import model.Request;
import model.User;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet handling request-related operations (create, list, review leave requests)
 */
public class RequestServlet extends HttpServlet {
    private RequestDAO requestDAO = new RequestDAO();
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            response.sendRedirect("index.jsp");
            return;
        } else if (action.equals("/create")) {
            request.getRequestDispatcher("leave_request.jsp").forward(request, response);
        } else if (action.equals("/list")) {
            List<Request> requests = requestDAO.getRequestsByUser(user.getId());
            request.setAttribute("requests", requests);
            request.getRequestDispatcher("request_list.jsp").forward(request, response);
        } else if (action.equals("/review")) {
            if (userDAO.hasRole(user.getId(), "Manager")) {
                List<Request> requests = requestDAO.getPendingRequestsForManager(user.getId());
                request.setAttribute("requests", requests);
                request.getRequestDispatcher("request_list.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        String action = request.getPathInfo();

        if (action.equals("/create")) {
            String fromDateStr = request.getParameter("fromDate");
            String toDateStr = request.getParameter("toDate");
            String reason = request.getParameter("reason");

            // Chuyển đổi String thành java.sql.Date
            java.sql.Date fromDate = null;
            java.sql.Date toDate = null;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date utilFromDate = sdf.parse(fromDateStr);
                java.util.Date utilToDate = sdf.parse(toDateStr);
                fromDate = new java.sql.Date(utilFromDate.getTime());
                toDate = new java.sql.Date(utilToDate.getTime());
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid date format. Use YYYY-MM-DD.");
                return;
            }

            Request requestObj = new Request();
            requestObj.setUserId(user.getId());
            requestObj.setFromDate(fromDate); // Sử dụng java.sql.Date
            requestObj.setToDate(toDate); // Sử dụng java.sql.Date
            requestObj.setReason(reason);
            requestObj.setStatus("Inprogress");
            requestObj.setCreatedBy(user.getId()); // Người tạo là người dùng hiện tại
            requestDAO.createRequest(requestObj);
            response.sendRedirect("request/list");
        } else if (action.equals("/review")) {
            int requestId = Integer.parseInt(request.getParameter("request_id"));
            String status = request.getParameter("status");
            if (!status.equals("Approve") && !status.equals("Reject")) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid status");
                return;
            }
            requestDAO.updateRequestStatus(requestId, status.equals("Approve") ? "Approved" : "Rejected", user.getId());
            response.sendRedirect("request/review");
        }
    }
}