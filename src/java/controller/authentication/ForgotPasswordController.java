/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.authentication;

import dal.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author phank
 */
public class ForgotPasswordController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/auth/forgotPassword.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        if (username == null || username.trim().isEmpty()) {
            req.setAttribute("error", "Username is required.");
            req.getRequestDispatcher("view/auth/forgotPassword.jsp").forward(req, resp);
            return;
        }

        UserDBContext db = new UserDBContext();
        User user = new User();
        user.setUsername(username);
        User existingUser = db.get(username, null); // Check if username exists (password not needed)

        if (existingUser != null) {
            // In a real application, send an email with a reset link here
            req.setAttribute("message", "A password reset link has been sent to your email (placeholder).");
        } else {
            req.setAttribute("error", "Username not found.");
        }
        req.getRequestDispatcher("iew/auth/forgotPassword.jsp").forward(req, resp);
    }
}
