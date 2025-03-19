/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.leaveRequest;

import controller.authentication.BaseRequiredAuthenticationController;
import dal.EmployeeDBContext;
import dal.LeaveRequestDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import model.Employee;
import model.LeaveRequest;
import model.User;

/**
 *
 * @author sonnt-local hand-some
 */
public class CreateLeaveRequestController extends BaseRequiredAuthenticationController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        LeaveRequest lr = new LeaveRequest();
        lr.setTitle(req.getParameter("title"));
        lr.setReason(req.getParameter("reason"));
        lr.setFrom(Date.valueOf(req.getParameter("from")));
        lr.setTo(Date.valueOf(req.getParameter("to")));
        lr.setCreatedby(user);
        Employee owner = new Employee();
        owner.setId(Integer.parseInt(req.getParameter("eid")));
        lr.setOwner(owner);
        
        LeaveRequestDBContext db = new LeaveRequestDBContext();
        db.insert(lr);
        resp.getWriter().println("inserted " + lr.getId());
        req.getRequestDispatcher("view/home/create.jsp").forward(req, resp);
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(user.getEmployee());
        for (Employee staff : user.getEmployee().getStaffs()) {
            employees.add(staff);
        }
        req.setAttribute("employees",employees);
        req.getRequestDispatcher("view/home/create.jsp").forward(req, resp);
    }
}
