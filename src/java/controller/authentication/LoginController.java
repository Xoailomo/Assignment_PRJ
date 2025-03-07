/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.authentication;

import dal.EmployeeDBContext;
import dal.UserDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import model.Employee;
import model.Role;
import model.User;

/**
 *
 * @author phank
 */
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            req.setAttribute("error", "Email and password are required");
            req.getRequestDispatcher("/view/auth/login.jsp").forward(req, resp);
            return;
        }
        UserDBContext db = new UserDBContext();
        User user = db.get(username, password);
        if (user != null) {
            EmployeeDBContext edb = new EmployeeDBContext();
            Employee profile = edb.get(user.getEmployee().getId());
            if (profile != null) {
                user.getEmployee().setManager(profile.getManager());
                user.setEmployee(profile);
            } else {
                System.out.println("Employee profile not found for user: " + username);
                req.setAttribute("error", "User Profile not found. Please contact support.");
                req.getRequestDispatcher("../view/auth/login.jsp").forward(req, resp);
                return;
            }
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            resp.sendRedirect("index.jsp");

        } else {
            System.out.println("Authentication faild for email: " + username);
            req.setAttribute("error", "Invalid email or password");
            req.getRequestDispatcher("view/auth/login.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/auth/login.jsp").forward(req, resp);
    }

}
