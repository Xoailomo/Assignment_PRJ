<%-- 
    Document   : agenda
    Created on : Mar 4, 2025, 6:01:58 PM
    Author     : phank
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<!--        <style>
        body {
            display: flex;
            font-family: Arial, sans-serif;
            margin: 0;
        }
        .sidebar {
            width: 200px;
            background: #f4f4f4;
            padding: 10px;
            height: 100vh;
        }
        .sidebar ul {
            list-style: none;
            padding: 0;
        }
        .sidebar ul li {
            padding: 10px;
            cursor: pointer;
        }
        .sidebar ul li:hover, .sidebar ul li.active {
            background: #007bff;
            color: white;
        }
        .content {
            flex: 1;
            padding: 20px;
        }
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .calendar-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        .calendar-table th, .calendar-table td {
            border: 1px solid #ccc;
            padding: 8px;
            text-align: center;
        }
        .leave-dot {
            width: 10px;
            height: 10px;
            background: green;
            border-radius: 50%;
            display: inline-block;
        }
    </style>-->
    </head>
    <body>
        <div class="container">
            <!-- Sidebar -->
            <div class="sidebar">
                <jsp:include page="../home/sidebar.jsp"/>     
            </div>
            <main class="main-content">
                <section>

                    <div class="content">
                        <div class="header">
                            <button>Company Calendar</button>
                            <button>My Calendar</button>
                        </div>
                        <div>
                            <label>Range</label>
                            <input type="text" placeholder="03/05/2025 - 04/05/2025">
                        </div>
                        <table class="calendar-table">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>We 5</th>
                                    <th>Th 6</th>
                                    <th>Fr 7</th>
                                    <th>Sa 8</th>
                                    <th>Su 9</th>
                                    <th>Mo 10</th>
                                    <th>Tu 11</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>111,111</td>
                                    <td><span class="leave-dot"></span></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td><span class="leave-dot"></span></td>
                                </tr>
                                <tr>
                                    <td>dfdfdf, dfdfdf</td>
                                    <td><span class="leave-dot" style="background: red;"></span></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </section></main>
        </div>


    </body>
</html>
