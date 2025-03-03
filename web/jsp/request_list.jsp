<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>List Requests - Leave Management System</title>
    <link rel="stylesheet" href="../css/styles.css">
</head>
<body>
    <%-- Bao gồm header.jsp --%>
    <jsp:include page="header.jsp" />
    
    <h2>List Requests</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>User</th>
            <th>From Date</th>
            <th>To Date</th>
            <th>Reason</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="request" items="${requests}">
            <tr>
                <td>${request.requestId}</td>
                <td>${request.user.name}</td>
                <td>${request.fromDate}</td>
                <td>${request.toDate}</td>
                <td>${request.reason}</td>
                <td>${request.status}</td>
                <c:if test="${sessionScope.user.hasRole('Manager') && request.status == 'Inprogress'}">
                    <td>
                        <form action="../request/review" method="post">
                            <input type="hidden" name="request_id" value="${request.requestId}">
                            <input type="submit" name="status" value="Approve">
                            <input type="submit" name="status" value="Reject">
                        </form>
                    </td>
                </c:if>
                <c:if test="${not sessionScope.user.hasRole('Manager') || request.status != 'Inprogress'}">
                    <td>-</td>
                </c:if>
            </tr>
        </c:forEach>
    </table>

    <%-- Bao gồm footer.jsp --%>
    <jsp:include page="footer.jsp" />
</body>
</html>