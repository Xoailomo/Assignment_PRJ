<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>List Requests - Leave Management System</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
    </head>
    <body>
        <div class="layout-container">
            <!-- Sidebar is included and fixed on the left -->
            <jsp:include page="../home/sidebar.jsp"/>

            <!-- Main Content on the right -->
            <main class="main-content">
                <h2>List Requests</h2>
                <table class="list-requests">
                    <thead>
                        <tr>
                            <th>Title</th>
                            <th>From Date</th>
                            <th>To Date</th>
                            <th>Created By</th>
                            <th>Status</th>
                            <th>Processed By</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="request" items="${requests}">
                        <table>

                            <tr>
                                <td>${request.title}</td>
                                <td>${request.fromDate}</td>
                                <td>${request.toDate}</td>
                                <td>${request.user.name}</td>
                                <td>${request.status}</td>
                                <td>${request.manager.name}</td>
                                <c:choose>
                                    <c:when test="${sessionScope.user.hasRole('Manager') && request.status == 'Inprogress'}">
                                        <td>
                                            <form action="../request/review" method="post">
                                                <input type="hidden" name="request_id" value="${request.requestId}">
                                                <input type="submit" name="status" value="Approve">
                                                <input type="submit" name="status" value="Reject">
                                            </form>
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>-</td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                            </tbody>
                        </c:forEach>
                    </table>
                </table>
            </main>
        </div>
    </body>
</html>
