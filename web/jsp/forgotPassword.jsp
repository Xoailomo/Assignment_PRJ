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