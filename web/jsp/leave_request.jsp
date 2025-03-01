<%-- 
    Document   : leave_request
    Created on : Feb 27, 2025, 9:43:54 PM
    Author     : phank
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<title>JSP Page</title>-->
    </head>
    <body>
        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <%@ include file="../header.jsp" %>
        <div class="container">
            <h2>Create Leave Request</h2>
            <form action="<c:url value='/RequestServlet' />" method="post">
                <input type="hidden" name="action" value="create" />
                <div>
                    <label for="fromDate">From Date:</label>
                    <input type="date" name="fromDate" id="fromDate" required />
                </div>
                <div>
                    <label for="toDate">To Date:</label>
                    <input type="date" name="toDate" id="toDate" required />
                </div>
                <div>
                    <label for="requestTitle">Title:</label>
                    <input type="text" name="requestTitle" id="requestTitle" placeholder="Short description" required />
                </div>
                <div>
                    <label for="reason">Reason:</label>
                    <textarea name="reason" id="reason" rows="4" cols="50" required></textarea>
                </div>
                <div>
                    <button type="submit">Submit Request</button>
                </div>
            </form>
        </div>
        <%@ include file="../footer.jsp" %>

    </body>
</html>
