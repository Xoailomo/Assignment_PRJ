<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Leave Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
    <div class="login-container">
        <div class="login-header">
            <h2><i class="fas fa-sign-in-alt"></i> Login to LeaveBoard</h2>
            <p>Please enter your username and password to access your account.</p>
        </div>
        <c:if test="${not empty error}">
            <div class="error-message">
                <p><i class="fas fa-exclamation-circle"></i> ${error}</p>
            </div>
        </c:if>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="form-group">
                <label for="username"><i class="fas fa-user"></i> Username:</label> <!-- Changed from Email to Username -->
                <input type="text" id="username" name="username" placeholder="Enter your username" required value="${cookie.username.value != null ? cookie.username.value : ''}">
            </div>
            <div class="form-group">
                <label for="password"><i class="fas fa-lock"></i> Password:</label>
                <input type="password" id="password" name="password" placeholder="Enter your password" required>
            </div>
            <div class="form-group checkbox-group">
                <input type="checkbox" id="rememberMe" name="rememberMe">
                <label for="rememberMe">Remember Me</label>
            </div>
            <button type="submit" class="login-button"><i class="fas fa-sign-in-alt"></i> Login</button>
        </form>
        <div class="login-footer">
            <p>Don't have an account? Contact your HR administrator.</p>
        </div>
    </div>
</body>
</html>