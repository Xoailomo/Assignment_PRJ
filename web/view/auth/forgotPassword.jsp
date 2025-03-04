<%-- 
    Document   : forgotPassword
    Created on : Mar 4, 2025, 12:35:31 AM
    Author     : phank
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Forgot Password - Employee Leave Management System</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/styles.css">
        <style>
            /* styles.css */

            /* Container for the forgot password form */
            .login-container {
                width: 400px;                /* Adjust width as needed */
                margin: 80px auto;           /* Center horizontally with some top margin */
                padding: 20px;
                background-color: #ffffff;
                border: 1px solid #ddd;
                border-radius: 8px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                text-align: center;
            }

            /* Heading */
            .login-container h2 {
                margin-bottom: 15px;
                color: #333;
            }

            /* Paragraph styling (optional) */
            .login-container p {
                margin-bottom: 20px;
                color: #555;
            }

            /* Group for label + input */
            .input-group {
                margin-bottom: 15px;
                text-align: left;
            }

            /* Label */
            .input-group label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
                color: #333;
            }

            /* Input field */
            .input-group input[type="email"] {
                width: 100%;
                padding: 8px 12px;
                border: 1px solid #ccc;
                border-radius: 4px;
                font-size: 1em;
                box-sizing: border-box;
            }

            /* Reset button */
            .reset-btn {
                background-color: #007bff; /* Primary color, can be changed */
                color: #fff;
                border: none;
                padding: 10px 16px;
                font-size: 1em;
                cursor: pointer;
                border-radius: 4px;
                margin-top: 10px;
                transition: background-color 0.3s ease;
            }

            .reset-btn:hover {
                background-color: #0056b3;
            }

            /* Optional arrow styling on the button */
            .reset-btn .arrow {
                margin-left: 5px;
                font-weight: bold;
            }

            /* Error message */
            .error-message {
                color: #f44336; /* Red */
                font-weight: bold;
                margin-top: 10px;
            }

        </style>
    </head>
    <body>
        <div class="login-container">
            <h2>Forgot Password</h2>
            <p>Nhập email để nhận liên kết đặt lại mật khẩu.</p>

            <!-- Form yêu cầu đặt lại mật khẩu -->
            <form action="<%=request.getContextPath()%>/auth/forgotpassword" method="post">
                <div class="input-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" placeholder="Nhập email" required>
                </div>
                <button type="submit" class="reset-btn">Send Reset Link <span class="arrow">→</span></button>
            </form>

            <!-- Thông báo lỗi nếu có -->
            <% if (request.getAttribute("error") != null) { %>
            <p class="error-message"><%= request.getAttribute("error") %></p>
            <% } %>
        </div>
    </body>
</html>