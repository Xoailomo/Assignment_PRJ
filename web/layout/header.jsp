<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<header class="header">
    <div style="background: #0056b3; color:white; padding: 10px; text-align: center">
        <h1>Employee Leave Management System</h1>
        <c:if test="${sessionScope.user != null}">
            <span>Welcome, ${sessionScope.user.employee.name}</span>
        </c:if>
    </div>
</header>