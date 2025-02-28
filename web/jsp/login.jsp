<%-- 
    Document   : login
    Created on : Feb 27, 2025, 9:17:54 PM
    Author     : phank
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action="AuthServelt" method="post">
            <label for="email">Email:</label>
            <input type="email" name="email" id="email" required /></br>
            <label for="password">Password:</label>
            <input type="password" name="password" id="password" required/></br>
            
            <button type="submit">Login</button>
        </form>
        <p><a href="register.jsp">Register</a>(if allowed)</p>
        <%@ include file="footer.jsp"%>
    </body>
</html>
