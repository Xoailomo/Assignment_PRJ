<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Employee Leave Management System</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
    <div class="login-container">
        <h2>Employee Leave Management System</h2>
        
        <!-- Form đăng nhập -->
        <form action="<%=request.getContextPath()%>/auth/login" method="post">
            <div class="input-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" placeholder="Nhập email" required>
            </div>
            <div class="input-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="Nhập mật khẩu" required>
            </div>
            <button type="submit" class="login-btn">Login <span class="arrow">→</span></button>
        </form>
        
        <!-- Liên kết quên mật khẩu -->
        <a href="<%=request.getContextPath()%>/jsp/forgotPassword.jsp" class="forgotPassword">Forgot password</a>
        
        <!-- Thông báo lỗi nếu có -->
        <% if (request.getAttribute("error") != null) { %>
            <p class="error-message"><%= request.getAttribute("error") %></p>
        <% } %>
    </div>
</body>
</html>