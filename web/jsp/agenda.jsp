<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Agenda - Leave Management System</title>
    <link rel="stylesheet" href="../css/styles.css">
</head>
<body>
    <%-- Bao gồm header.jsp --%>
    <jsp:include page="header.jsp" />
    
    <h2>Agenda</h2>
    <form action="../agenda" method="get">
        <label>From Date:</label>
        <input type="date" name="fromDate" required>
        <label>To Date:</label>
        <input type="date" name="toDate" required>
        <input type="submit" value="View">
    </form>
    <c:if test="${not empty requests}">
        <table border="1">
            <tr>
                <th>User</th>
                <th>From Date</th>
                <th>To Date</th>
                <th>Reason</th>
            </tr>
            <c:forEach var="request" items="${requests}">
                <tr>
                    <td>${request.user.name}</td>
                    <td>${request.fromDate}</td>
                    <td>${request.toDate}</td>
                    <td>${request.reason}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <%-- Bao gồm footer.jsp --%>
    <jsp:include page="footer.jsp" />
</body>
</html>