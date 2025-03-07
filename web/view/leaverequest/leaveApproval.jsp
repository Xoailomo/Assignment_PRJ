<%-- 
    Document   : leaveApproval
    Created on : Mar 4, 2025, 6:04:53 PM
    Author     : phank
--%>

            <jsp:include page="../home/sidebar.jsp"/> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!DOCTYPE html>
    <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Leaves to Approve</title>
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
            <!--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">-->
            <style>
                body {
                    font-family: Arial, sans-serif;
                    margin: 20px;
                }
                .container {
                    max-width: 800px;
                }
                .leave-card {
                    border: 1px solid #ccc;
                    padding: 15px;
                    border-radius: 5px;
                    margin-top: 10px;
                }
                .leave-header {
                    font-weight: bold;
                }
                .leave-type {
                    display: flex;
                    align-items: center;
                }
                .leave-type .dot {
                    width: 10px;
                    height: 10px;
                    background: yellow;
                    border-radius: 50%;
                    display: inline-block;
                    margin-right: 5px;
                }
                .buttons {
                    margin-top: 10px;
                }
                .approve-btn, .decline-btn {
                    padding: 5px 10px;
                    border: none;
                    cursor: pointer;
                    border-radius: 3px;
                }
                .approve-btn {
                    background-color: green;
                    color: white;
                }
                .decline-btn {
                    background-color: red;
                    color: white;
                }
            </style>
        </head>
        <body>
            <main class="main-content">
                <div class="container">
                    <h3>Leaves to approve</h3>
                    <label for="date-range">Range</label>
                    <input type="text" id="date-range" value="03/05/2024 - 04/04/2026" readonly>

                    <div class="leave-card">
                        <div class="leave-header">111, 111</div>
                        <div class="leave-type">
                            <span class="dot"></span>
                            Vacation
                        </div>
                        <div>03/06/2025 - a day</div>
                        <div class="buttons">
                            <button class="approve-btn"><i class="fas fa-thumbs-up"></i> Approve</button>
                            <button class="decline-btn"><i class="fas fa-thumbs-down"></i> Decline</button>
                        </div>
                    </div>
                </div>
            </main>










            <!--        <div class="container">
             
             Main Content 
            <main class="main-content">
                <section class="leave-approval">
                    <h2>Pending Leave Requests</h2>
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
                    <table>
                        <thead>
                            <tr>
                                <th>Employee</th>
                                <th>Leave Type</th>
                                <th>Start Date</th>
                                <th>End Date</th>
                                <th>Comment</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="request" items="${pendingRequests}">
                            <tr>
                                <td>${request.employee.name}</td>
                                <td>${request.leaveType}</td>
                                <td><fmt:formatDate value="${request.startDate}" pattern="dd/MM/yyyy"/></td>
                            <td><fmt:formatDate value="${request.endDate}" pattern="dd/MM/yyyy"/></td>
                            <td>${request.comment}</td>
                            <td>
                                <form action="${pageContext.request.contextPath}/leaveApproval" method="post" style="display:inline;">
                                    <input type="hidden" name="requestId" value="${request.id}">
                                    <button type="submit" name="action" value="Approved">Approve</button>
                                    <button type="submit" name="action" value="Rejected">Reject</button>
                                </form>
                            </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </section>
            </main>
        </div>-->
        </body>
    </html>
