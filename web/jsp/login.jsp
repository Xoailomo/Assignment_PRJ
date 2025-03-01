<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - Leave Management System</title>
    <link rel="stylesheet" href="../css/styles.css">
</head>
<body>
    <%-- Bao gồm header.jsp --%>
    <jsp:include page="header.jsp" />
    
    <h2>Login</h2>
    <c:if test="${not empty error}">
        <p style="color: red">${error}</p>
    </c:if>
    <form action="auth/login" method="post">
        <div>
            <label>Email:</label>
            <input type="email" name="email" required>
        </div>
        <div>
            <label>Password:</label>
            <input type="password" name="password" required>
        </div>
        <div>
            <input type="submit" value="Login">
        </div>
    </form>

    <%-- Bao gồm footer.jsp --%>
    <jsp:include page="footer.jsp" />
</body>
</html>