<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : request_list
    Created on : Feb 27, 2025, 9:51:14 PM
    Author     : phank
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Leave Requests</title>
    </head>
    <body>
        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
        <%@ include file="../header.jsp" %>
        <div class="container">
            <h2>Leave Requests List</h2>

            <!-- Search and Filter Form -->
            <form action="<c:url value='/jsp/request_list.jsp' />" method="get">
                <input type="text" name="keyword" placeholder="Search by Title or Created By..."
                       value="${param.keyword}" />
                <select name="statusFilter">
                    <option value="">All Statuses</option>
                    <option value="Inprogress" <c:if test="${param.statusFilter eq 'Inprogress'}">selected</c:if>>Inprogress</option>
                    <option value="Approved" <c:if test="${param.statusFilter eq 'Approved'}">selected</c:if>>Approved</option>
                    <option value="Rejected" <c:if test="${param.statusFilter eq 'Rejected'}">selected</c:if>>Rejected</option>
                    </select>
                    <button type="submit">Search</button>
                </form>

                <!-- Requests Table -->
                <table border="1" cellpadding="5" cellspacing="0">
                    <thead>
                        <tr>
                            <th>Title</th>
                            <th>From</th>
                            <th>To</th>
                            <th>Created By</th>
                            <th>Status</th>
                            <th>Processed By</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:choose>
                        <c:when test="${not empty requestList}">
                            <c:forEach var="req" items="${requestList}">
                                <tr>
                                    <td>
                                        <c:choose>
                                            <c:when test="${fn:length(req.requestTitle) > 50}">
                                                ${fn:substring(req.requestTitle, 0, 50)}...
                                            </c:when>
                                            <c:otherwise>
                                                ${req.requestTitle}
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${req.fromDate}</td>
                                    <td>${req.toDate}</td>
                                    <td>${req.createdBy}</td>
                                    <td class="status-${req.requestStatus}">
                                        ${req.requestStatus}
                                    </td>
                                    <td>${req.processedBy}</td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="6">No requests found.</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>

            <!-- Pagination -->
            <c:if test="${totalPages gt 1}">
                <div class="pagination">
                    <c:if test="${currentPage gt 1}">
                        <a href="?page=${currentPage - 1}&keyword=${param.keyword}&statusFilter=${param.statusFilter}">&laquo; Prev</a>
                    </c:if>
                    <c:forEach var="p" begin="1" end="${totalPages}">
                        <a href="?page=${p}&keyword=${param.keyword}&statusFilter=${param.statusFilter}"
                           class="${p eq currentPage ? 'active' : ''}">${p}</a>
                    </c:forEach>
                    <c:if test="${currentPage lt totalPages}">
                        <a href="?page=${currentPage + 1}&keyword=${param.keyword}&statusFilter=${param.statusFilter}">Next &raquo;</a>
                    </c:if>
                </div>
            </c:if>
        </div>
        <%@ include file="../footer.jsp" %>

    </body>
</html>
