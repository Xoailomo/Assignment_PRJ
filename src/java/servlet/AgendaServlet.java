package servlet;

import dao.RequestDAO;
import dao.UserDAO;
import model.Request;
import model.User;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AgendaServlet extends HttpServlet {
    private RequestDAO requestDAO = new RequestDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        UserDAO userDAO = new UserDAO();
        if (!userDAO.hasRole(user.getId(),"DepartmentHead")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
            return;
        }

        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        List<Request> requests = requestDAO.getApprovedRequestsByDepartment(user.getDepartment().getId(), fromDate, toDate);
        request.setAttribute("requests", requests);
        request.getRequestDispatcher("agenda.jsp").forward(request, response);
    }
}