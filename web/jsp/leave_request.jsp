<%-- 
    Document   : leave_request
    Created on : Feb 27, 2025, 9:43:54 PM
    Author     : phank
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <h1>Create Leave Request</h1>
        <form action="RequestServlet" method="post">
            <input type="hidden" name="action" value="create"/>
            <label for="fromDate">From Date:</lable>
            <input type="date" name="fromDate" id="fromDate" required/></br>
            <label for="toDate">To Date:</label>
            <input type="date" name="todDate" id="toDate" required /></br>
            <label for="reason">Reason:</label>
            <texarea name="reason" id="reason" rows="4" cols="50" required></texarea></br>
            <button type="submit">Submit Request</button><!-- comment -->
        </form>
        <%@ include file="footer.jsp" %>
    </body>
</html>
