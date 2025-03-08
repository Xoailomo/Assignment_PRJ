/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.leaveRequest;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Request;

/**
 *
 * @author phank
 */
public class LeaveRequestController extends HttpServlet {

    private String getCurrentUserName(HttpServletRequest req) {
        return (String) req.getSession().getAttribute("username");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String leaveType = req.getParameter("leaveType");
        String fromStr = req.getParameter("startDate");
        String toStr = req.getParameter("endaDate");
        String comment = req.getParameter("comment");

        String username = getCurrentUserName(req);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date from = null;
        Date to = null;
        try {
            from = sdf.parse(fromStr);
            to = sdf.parse(toStr);
        } catch (Exception ex) {
            ex.printStackTrace();
            req.setAttribute("error", "invalid format.");
            return;
        }
        Request r = new Request();
        r.setLeaveType(leaveType);
        r.setFromDate(from);
        r.setToDate(to);
        r.setComment(comment);
        r.setStatus("Inprogress");
        
        resp.sendRedirect("../leaverequest/myleaves.jsp");
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("../home/dashboard.jsp").forward(req, resp);
    }

}
