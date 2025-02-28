<%-- 
    Document   : header
    Created on : Feb 27, 2025, 10:53:05 PM
    Author     : phank
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Leave Management System</title>
        <!--Use context-relative URLs for styles and scripts-->
    </head>
    <body>
        <header>
            <nav>
                <ul>
                    <!-- Use the context path to avoid relative path issues -->
                    <li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                    <li><a href="${pageContext.request.contextPath}/jsp/login.jsp">Login</a></li>
                    <li><a href="${pageContext.request.contextPath}/jsp/leave_request.jsp">Create Request</a></li>

                </ul>
            </nav>
        </header>

    </body>
</html>
