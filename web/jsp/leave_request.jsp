<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Create Request - Leave Management System</title>
    <link rel="stylesheet" href="../css/styles.css">
</head>
<body>
    <%-- Bao gồm header.jsp --%>
    <jsp:include page="header.jsp" />
    
    <h2>Create Leave Request</h2>
    <form action="request/create" method="post">
        <div>
            <label>From Date:</label>
            <input type="date" name="fromDate" required>
        </div>
        <div>
            <label>To Date:</label>
            <input type="date" name="toDate" required>
        </div>
        <div>
            <label>Reason:</label>
            <textarea name="reason" required></textarea>
        </div>
        <div>
            <input type="submit" value="Submit">
        </div>
    </form>

    <%-- Bao gồm footer.jsp --%>
    <jsp:include page="footer.jsp" />
</body>
</html>