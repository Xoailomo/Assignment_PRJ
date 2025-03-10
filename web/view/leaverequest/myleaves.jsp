<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>My Leaves - Leave Management System</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <!--        <style>
                    body {
                        font-family: Arial, sans-serif;
                        margin: 0;
                        padding: 20px;
                    }
                    .leave-details {
                        border: 1px solid #ccc;
                        padding: 10px;
                        border-radius: 5px;
                        max-width: 400px;
                    }
                    .leave-details .leave-type {
                        font-weight: bold;
                        display: flex;
                        align-items: center;
                    }
                    .leave-details .leave-type .dot {
                        width: 10px;
                        height: 10px;
                        background: orange;
                        border-radius: 50%;
                        display: inline-block;
                        margin-right: 5px;
                    }
                    .leave-details .info {
                        display: flex;
                        justify-content: space-between;
                        padding: 5px 0;
                    }
                </style>-->
    </head>
    <body>
<jsp:include page="/layout/header.jsp" />
        <div class="container">
            <div class="sidebar">
                <jsp:include page="../home/sidebar.jsp"/>     
            </div>
            <main class="main-content">
                <section class="leave-requests">
                    <h2>My Leaves</h2>

                    Filter Section 
                    <div class="filters-leave">
                        <form action="${pageContext.request.contextPath}/view/leaverequest/myleaves.jsp" method="get" class="filter-form">
                            <div class="filter-group">
                                <label for="startDate">Range:</label>
                                <input type="date" id="startDate" name="startDate" value="${param.startDate}">
                                <span>-</span>
                                <input type="date" id="endDate" name="endDate" value="${param.endDate}">
                            </div>
                            <div class="filter-group">
                                <label for="status">Status:</label>
                                <select id="status" name="status">
                                    <option value="">All</option>
                                    <option value="Inprogress" ${param.status == 'Inprogress' ? 'selected' : ''}>Pending Approval</option>
                                    <option value="Approved" ${param.status == 'Approved' ? 'selected' : ''}>Approved</option>
                                    <option value="Rejected" ${param.status == 'Rejected' ? 'selected' : ''}>Rejected</option>
                                </select>
                            </div>
                            <div class="filter-group">
                                <label for="leaveType">Leave Type:</label>
                                <select id="leaveType" name="leaveType">
                                    <option value="">All</option>
                                    <option value="Vacation" ${param.leaveType == 'Vacation' ? 'selected' : ''}>Vacation</option>
                                    <option value="Sick leave" ${param.leaveType == 'Sick leave' ? 'selected' : ''}>Sick Leave</option>
                                    <option value="Unpaid leave" ${param.leaveType == 'Unpaid leave' ? 'selected' : ''}>Unpaid Leave</option>
                                </select>
                            </div>
                            <button type="submit" class="filter-button"><i class="fas fa-filter"></i> Filter</button>
                        </form>
                    </div>

                    Leave Requests 
                    <c:if test="${not empty message}">
                        <div class="message">
                            <p><i class="fas fa-info-circle"></i> ${message}</p>
                        </div>
                    </c:if>
                    <c:if test="${not empty error}">
                        <div class="error-message">
                            <p><i class="fas fa-exclamation-circle"></i> ${error}</p>
                        </div>
                    </c:if>
                    <div class="leave-list">
                        <c:choose>
                            <c:when test="${not empty myRequests}">
                                <c:forEach var="request" items="${myRequests}">
                                    <div class="leave-item">
                                        <div class="leave-details">
                                            <span class="leave-type-dot ${request.leaveType == 'Vacation' ? 'vacation' : request.leaveType == 'Sick leave' ? 'sick-leave' : 'unpaid-leave'}"></span>
                                            <span class="leave-type">${request.leaveType}</span>
                                            <span class="leave-date">
                                                <fmt:formatDate value="${request.startDate}" pattern="dd/MM/yyyy"/>
                                                <c:if test="${request.startDate != request.endDate}">
                                                    - <fmt:formatDate value="${request.endDate}" pattern="dd/MM/yyyy"/>
                                                </c:if>
                                            </span>
                                            <span class="leave-duration">
                                                <c:set var="days" value="${(request.endDate.time - request.startDate.time) / (1000 * 60 * 60 * 24) + 1}"/>
                                                (${days} day${days > 1 ? 's' : ''})
                                            </span>
                                            <span class="leave-status">
                                                <c:choose>
                                                    <c:when test="${request.status == 'Inprogress'}">Pending Approval</c:when>
                                                    <c:when test="${request.status == 'Approved'}">Approved by ${request.processedBy != null ? request.processedBy.name : 'N/A'}</c:when>
                                                    <c:when test="${request.status == 'Rejected'}">Rejected by ${request.processedBy != null ? request.processedBy.name : 'N/A'}</c:when>
                                                    <c:otherwise>${request.status}</c:otherwise>
                                                </c:choose>
                                            </span>
                                        </div>
                                        <form action="${pageContext.request.contextPath}/view/leaverequest/myleaves.jsp" method="post" class="cancel-form">
                                            <input type="hidden" name="action" value="cancel">
                                            <input type="hidden" name="requestId" value="${request.id}">
                                            <button type="submit" class="cancel-button" <c:if test="${request.status != 'Inprogress'}">disabled</c:if>>
                                                    <i class="fas fa-times"></i> Cancel Request
                                                </button>
                                            </form>
                                        </div>
                                </c:forEach>

                            </c:when>
                            <c:otherwise>
                                1
                                <div class="leave-details">
                                    <div class="leave-type">
                                        <span class="dot"></span>
                                        Sick leave
                                    </div>
                                    <div class="info">
                                        <span>03/05/2025</span>
                                        <span>a day</span>
                                    </div>
                                    <div class="info">
                                        <span>Approved by hai hai</span>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </section>
            </main>
        </div>
<jsp:include page="/layout/footer.jsp" />   
    </body>
</html>