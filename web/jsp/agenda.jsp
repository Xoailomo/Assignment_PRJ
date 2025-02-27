<%-- 
    Document   : agenda
    Created on : Feb 27, 2025, 10:59:18 PM
    Author     : phank
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
<!--        <style>
        table, th, td {
            border: 1px solid #999;
            border-collapse: collapse;
            text-align: center;
            padding: 5px;
        }
        .present {
            background-color: #c8e6c9; /* Xanh nhạt */
        }
        .absent {
            background-color: #ffcdd2; /* Đỏ nhạt */
        }
    </style>-->
    </head>
    <body>
        <h1>Attendance overview</h1>

        <c:if test="${not empty employeeList and not empty dateRange}">
            <table>
                <thead>
                    <tr>
                        <th>Staff</th>
                        <!-- reqeat the daily list to create cols-->
                        <c:forEach var="d" items="${dateRange}">
                            <th><c:out value="${d}"/></th>
                            </c:forEach>

                    </tr>
                </thead>
                <tbody>

                    <!-- repeat staff list-->
                    <c:forEach var="emp" items="${dateRange}">
                    <td class="<c:out value='$emp.isAbsent(d)?\"absent\" : \"present\"}'/>">
                    </td>
                    </c:forEach>
                    </tbody>
        </table>
        </c:if>
        <c:if test="${empty employeeList and not empty dateRange}">
            <p> No data to show</p>
        </c:if>
            <p><a href="index.jsp">Back to home</a></p>
            </body>
        </html>
