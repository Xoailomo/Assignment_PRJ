<%-- 
    Document   : header
    Created on : Feb 27, 2025, 10:53:05 PM
    Author     : phank
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Leave Management System</title>
        <link rel="stylesheet" type="text/css" href="<c:url value='/styles.css' />" />
        <script src="<c:url value='/js/main.js' />"></script>
    </head>
    <body>
        <header>
            <div class="container">
                <h1>Leave Management System</h1>
                <nav>
                    <ul>
                        <li><a href="index.jsp">Home</a></li>
                <li><a href="jsp/login.jsp">Login</a></li>
                <li><a href="jsp/leave_request.jsp">Create Request</a></li>
                <li><a href="jsp/request_list.jsp">List Requests</a></li>
                <li><a href="jsp/review_request.jsp">Review Request</a></li>
                <li><a href="jsp/agenda.jsp">Agenda</a></li>
                        <c:if test="${not empty sessionScope.loggedInUser}">
                            <li><a href="<c:url value='/logout' />">Logout</a></li>
                        </c:if>
                    </ul>
                </nav>
            </div>
    </body>
</html>
