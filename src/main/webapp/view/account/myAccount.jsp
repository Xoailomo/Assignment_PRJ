<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User Profile</title>
        <link href="../../css/dashboard.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 20px;
            }
            .container {
                max-width: 800px;
            }
            .profile-card {
                border: 1px solid #ccc;
                padding: 15px;
                border-radius: 5px;
                margin-top: 10px;
            }
            .profile-header {
                font-weight: bold;
                font-size: 20px;
            }
            .info {
                margin-top: 10px;
            }
            .work-hours {
                display: flex;
                flex-wrap: wrap;
            }
            .work-day {
                border: 1px solid #00aaff;
                padding: 10px;
                margin: 5px;
                border-radius: 5px;
                min-width: 120px;
            }
            .approvers {
                margin-top: 15px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="sidebar">
                <jsp:include page="../home/sidebar.jsp"/>     
            </div>
            <main class="main-content">
                <div class="container">
                    <h3>User Profile</h3>
                    <div class="profile-card">
                        <div class="profile-header"><%= request.getAttribute("name") %></div>
                        <div class="info"><strong>Email:</strong> <a href="mailto:<%= request.getAttribute("email") %>"><%= request.getAttribute("email") %></a></div>
                        <div class="info"><strong>Position:</strong> <%= request.getAttribute("position") %></div>
                        <div class="info"><strong>Approver:</strong> <%= request.getAttribute("approver") %></div>
                        <div class="info"><strong>Team:</strong> <%= request.getAttribute("team") %></div>
                        <div class="info"><strong>Office:</strong> <%= request.getAttribute("office") %></div>
                        <div class="info"><strong>Country:</strong> <%= request.getAttribute("country") %></div>

                        <h4>Working Days and Hours</h4>
                        <div class="work-hours">
                            <div class="work-day">Monday<br>09:00 - 17:59</div>
                            <div class="work-day">Tuesday<br>09:00 - 17:59</div>
                            <div class="work-day">Wednesday<br>09:00 - 17:59</div>
                            <div class="work-day">Thursday<br>09:00 - 17:59</div>
                            <div class="work-day">Friday<br>09:00 - 17:59</div>
                        </div>
                    </div>

                    <div class="approvers">
                        <h4>Team Manager for</h4>
                        <p>👥 <%= request.getAttribute("team") %></p>
                        <h4>Approver for</h4>
                        <p>👤 <%= request.getAttribute("approverFor1") %></p>
                        <p>👤 <%= request.getAttribute("approverFor2") %></p>
                        <p>👤 <%= request.getAttribute("approverFor3") %></p>
                    </div>
                </div>

            </main>
        </div>
    </body>
</html>
