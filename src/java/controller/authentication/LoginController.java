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
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            req.setAttribute("error", "Email and password are required");
            req.getRequestDispatcher("/view/home/dashboard.jsp").forward(req, resp);
            return;
        }
        UserDBContext db = new UserDBContext();
        User user = db.get(email, password);
        if (user != null) {
            EmployeeDBContext edb = new EmployeeDBContext();
            Employee profile = edb.get(user.getEmployee().getId());
            if (profile != null) {
                user.getEmployee().setManager(profile.getManager());
                user.setEmployee(profile);
            } else {
                System.out.println("Employee profile not found for user: " + email);
                req.setAttribute("error", "User Profile not found. Please contact support.");
                req.getRequestDispatcher("../jsp/login.jsp").forward(req, resp);
                return;
            }
            // kiểm tra xem có role hay không

            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/login");

        } else {
            System.out.println("Authentication faild for email: "+email);
            req.setAttribute("error","Invalid email of password");
            req.getRequestDispatcher("../jsp/login.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
    }

}
