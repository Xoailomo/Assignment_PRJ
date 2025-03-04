<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<header class="header">
    <h1>Employee Leave Management System</h1>
    <c:if test="${sessionScope.user != null}">
        <div class="user-info">
            <span>Welcome, ${sessionScope.user.employee.name}</span>
            <a href="${pageContext.request.contextPath}/logout" class="logout-link"><i class="fas fa-sign-out-alt"></i> Logout</a>
        </div>
    </c:if>
</header>