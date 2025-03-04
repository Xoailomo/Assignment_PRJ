<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title> Leave Management System</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    </head>
    <body>
        <%--<jsp:include page="jsp/header.jsp" />--%>

        <div class="container">
            <h2>Welcome to the Leave Management System</h2>

            <!-- Check if user is logged in -->
            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                    <p>Hello, ${sessionScope.user.fullName}!</p>
                    <p>Access your dashboard using the navigation menu above.</p>
                </c:when>
                <c:otherwise>
                    <p>You are not logged in. Please <a href="login.jsp">login</a> to continue.</p>
                </c:otherwise>
            </c:choose>
        </div>

        <!-- Include common footer -->
        <%--<jsp:include page="jsp/footer.jsp" />--%>
    </body>
</html>