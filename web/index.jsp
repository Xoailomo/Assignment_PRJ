<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Leave Management System</title>
    <!-- Use context path for CSS, or a relative path if your CSS is at ../css/style.css -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css" />
</head>
<body>
    <div class="container">
        <h2>Welcome to the Leave Management System</h2>

        <!-- Check if user is logged in -->
        <c:choose>
            <c:when test="${not empty sessionScope.user}">
                <p>Hello, ${sessionScope.user.fullName}!</p>
                <p>Access your dashboard using the navigation menu above.</p>
            </c:when>
            <c:otherwise>
                <!-- Since index.jsp and login.jsp are in the same folder (jsp), 
                     a simple relative link 'login.jsp' works. -->
                <p>You are not logged in. Please 
                    <a href="jsp/login.jsp">login</a> 
                    to continue.
                </p>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
