<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Leave Management System</title>
    <meta http-equiv="refresh" content="0;url=${pageContext.request.contextPath}/login">
</head>
<body>
    <c:if test="${sessionScope.user != null}">
        <c:redirect url="/create"/>
    </c:if>
    <c:if test="${sessionScope.user == null}">
        <c:redirect url="/login"/>
    </c:if>
</body>
</html>