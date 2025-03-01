<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Leave Management System</title>
    <link rel="stylesheet" href="../css/styles.css">
</head>
<body>
    <%-- Bao gồm header.jsp --%>
    <jsp:include page="header.jsp" />
    
    <h1>Leave Management System</h1>
    
    <c:if test="${not empty sessionScope.user}">
        <ul>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="auth/login">Login</a></li>
            <li><a href="request/create">Create Request</a></li>
            <li><a href="request/list">List Requests</a></li>
            <c:if test="${sessionScope.user.hasRole('Manager')}">
                <li><a href="request/review">Review Request</a></li>
            </c:if>
            <c:if test="${sessionScope.user.hasRole('DepartmentHead')}">
                <li><a href="agenda.jsp">Agenda</a></li>
            </c:if>
            <li><a href="logout">Logout</a></li>
        </ul>
    </c:if>
    <c:if test="${empty sessionScope.user}">
        <p>Bạn chưa đăng nhập. Vui lòng <a href="auth/login">đăng nhập</a>.</p>
    </c:if>

    <%-- Bao gồm footer.jsp --%>
    <jsp:include page="footer.jsp" />
</body>
</html>