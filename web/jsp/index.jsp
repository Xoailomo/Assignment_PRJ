<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Leave Management System</title>
    <!-- Use context-relative URL for CSS -->
    <link rel="stylesheet" href="<c:url value='/css/styles.css' />" />
</head>
<body>
    <!-- Include header -->
    <jsp:include page="header.jsp" />

    <c:if test="${not empty sessionScope.user}">
        <ul>
            <li><a href="<c:url value='/index.jsp' />">Home</a></li>
            <!-- 
                If your actual servlet mapping for login is /auth/login, 
                use <c:url value='/jsp/login' />. 
                Otherwise, if you have a login.jsp, use <c:url value='login.jsp' /> 
            -->
            <li><a href="<c:url value='login.jsp' />">Login</a></li>
            <li><a href="<c:url value='/request/create' />">Create Request</a></li>
            <li><a href="<c:url value='/request/list' />">List Requests</a></li>

            <!-- Manager role check -->
            <c:if test="${sessionScope.user.hasRole('Manager')}">
                <li><a href="<c:url value='/request/review' />">Review Request</a></li>
            </c:if>

            <!-- DepartmentHead role check -->
            <c:if test="${sessionScope.user.hasRole('DepartmentHead')}">
                <li><a href="<c:url value='agenda.jsp' />">Agenda</a></li>
            </c:if>

            <li><a href="<c:url value='/logout' />">Logout</a></li>
        </ul>
    </c:if>

    <c:if test="${empty sessionScope.user}">
        <p>You are not logged in. Please 
            <a href="<c:url value='/login' />">Login</a>.
        </p>
    </c:if>

    <!-- Include footer -->
    <jsp:include page="footer.jsp" />
</body>
</html>
