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
        <link href="../../resources/css/t-datepicker.min.css" rel="stylesheet" type="text/css"/>
        <link href="../../resources/css/t-datepicker-main.css" rel="stylesheet" type="text/css"/>
        <script src="../../resources/js/t-datepicker.min.js" type="text/javascript"></script>
        <script src="../../resources/js/t-datepicker-v1.0.0.js" type="text/javascript"></script>
        <script src="../../resources/js/t-datepicker.js" type="text/javascript"></script>


    </head>
    <body>
        <jsp:include page="/layout/header.jsp" />
        <!--<div class="container">-->
        <!-- Sidebar -->
        <div class="sidebar">
            <jsp:include page="../home/sidebar.jsp"/>     
        </div>

        <form action="calendar" method="POST">
            <div class="t-datepicker">
                <div class="t-check-in"></div>
                <div class="t-check-out"></div>
            </div>
            <!-- Initialize tDatePicker -->
            <script>
                $(document).ready(function () {
                    $('.t-datepicker').tDatePicker({
                        autoClose: true,
                        limitNextMonth: 3,
                        numCalendar: 2,
                        dateRangesHover: true
                    });
                });
            </script>

            <!--</div>-->

            <jsp:include page="/layout/footer.jsp" />   
    </body>
</html>
