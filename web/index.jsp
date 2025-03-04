<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Leave Management System</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css" />
</head>
<body>
    <div class="container">
        <h2>Welcome to the Leave Management System</h2>

        <c:choose>
            <c:when test="${not empty sessionScope.user}">
                <p>Hello, ${sessionScope.user.fullName}!</p>
                <!-- Optionally auto-redirect to home.jsp:
                     <c:redirect url='/jsp/home.jsp' /> 
                     or just show a link: -->
                <p><a href="<%= request.getContextPath() %>/jsp/home.jsp">Go to Home</a></p>
            </c:when>
            <c:otherwise>
                <p>You are not logged in. Please 
                    <a href="<%= request.getContextPath() %>/jsp/login.jsp">login</a> 
                    to continue.
                </p>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
