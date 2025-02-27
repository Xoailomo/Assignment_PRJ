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
        <h1>List of Leave Requests</h1>
        <!-- Searh bar and filter status -->
        <form action="/request/list" method="get">
            <input type="text" name="keyword" placeholder="Search by Title or Created by"
                   value="${param.keyword}"/>
            <select name="statusFilter">
                <option value="">All Statuses></option>
                <option value="Inprogress"><c: if test="${pram.statusFilter eq 'Inprogress'}">selected</c:if>>Inprogress</option>
                <option value="Approved"><c: if test="${pram.statusFilter eq 'Approved'}">selected</c:if>>Approved</option>
                    <option value="Rejected"><c: if test="${pram.statusFilter eq 'Rejected'}">selected</c:if>>Rejected</option>
                    </select>
                    <button type="submit">Search</button>
                </form>
                <!-- display request lists-->
                <table border="1" width="100%" cellpadding="5" cellspacing="0">
                    <thead> 
                        <tr>
                            <th>Title</th>
                            <th>From</th>
                            <th>To</th>
                            <th>Created By</th>
                            <th>Status</th>
                            <th>Processed by</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${not empty requestList}">
                                <c:forEach var="req" items="${requestList}">
                                    <tr>
                                        <!--title-->
                                        <td>${req.requestTile}</td>
                                        <td>${req.fromDate}</td>
                                        <td>${req.toDate}</td>
                                        <td>${req.createBy}</td>

                                        <!--link class follow status to change color-->
                                        <td class="status-${req.requestStatus}">
                                            ${req.requestStatus}
                                        </td>
                                        <td>${req.procressBy}</td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="6">No request found.</td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
                <!-- paging-->
                <c:if test="${totalPages >1}">
                    <div class="pagination">
                        <c: if test="${currentPage > 1}">
                            <a href="?page=$currentPage - 1}&keyword=${param.keyword}&statusFilter=${param.statusFilter}">$laquo; Prev</a>
                    </c:if>
                    <c:forEach var="p" begin="1" end="${totalPages}">
                        <a href ="?page=${p}&keyword=${param.keyword}&statusFilter=${param.statusFilter}"
                           class ="${p == currentPage ? 'active' : ''}">${p}</a>
                    </c:forEach>
                    <c:if test="${currentPage < totalPages}">
                        <a href ="?page="$currentPage + 1}&keyword=${param.keyword}&statusFilter=${param.statusFilter}">Next &raquo;</a>
                    </c:if>
                </div>
                </c:if>
                <p><a href="index.jsp">Back to Home</a></p>
            </body>
        </html>
