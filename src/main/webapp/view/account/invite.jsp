<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Invite Employees</title>
        <link href="../../css/dashboard.css" rel="stylesheet" type="text/css"/>
        <style>
            .container {
                display: grid;
                grid-template-columns: 250px auto;
                gap:20px;

            }
            .sidebar{
                background: #333333;
                padding: 15px;
                color: black;
            }
            .sidebar.collapse{
                width:60px;
            }
            .main-content.collapse{
                margin-left: 60px;
            }

            body {
                font-family: 'Segoe UI', Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f5f7fa;
                display: flex;
                height: 100vh;
            }
            .sidebar {
                width: 220px;
                background-color: #fff;
                padding: 15px 0;
                box-shadow: 2px 0 5px rgba(0,0,0,0.1);
                height: 100%;
                overflow-y: auto;
            }
            .sidebar a {
                display: block;
                padding: 10px 20px;
                text-decoration: none;
                color: #333;
                font-size: 14px;
                line-height: 20px;
            }
            .sidebar a.active {
                background-color: #007bff;
                color: white;
                border-left: 3px solid #0056b3;
                padding-left: 17px;
            }
            .sidebar a:hover:not(.active) {
                background-color: #f8f9fa;
                color: #007bff;
            }
            .content {
                flex: 1;
                padding: 20px;
                overflow-y: auto;
                margin-left: 250px;
            }
            .invite-form {
                background-color: #fff;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 2px 5px rgba(0,0,0,0.1);
                max-width: 500px;
                margin: 0 auto;
            }
            .form-group {
                margin-bottom: 15px;
            }
            .form-group label {
                display: block;
                margin-bottom: 5px;
                font-size: 14px;
                color: #333;
            }
            .form-group input {
                width: 100%;
                padding: 8px;
                border: 1px solid #ced4da;
                border-radius: 4px;
                font-size: 14px;
                box-sizing: border-box;
            }
            .form-group input[type="date"] {
                color: #6c757d;
            }
            .radio-group {
                margin: 10px 0;
            }
            .radio-group input[type="radio"] {
                margin-right: 5px;
            }
            .submit-btn {
                background-color: #007bff;
                color: white;
                border: none;
                padding: 8px 20px;
                border-radius: 4px;
                cursor: pointer;
                font-size: 14px;
                font-weight: 600;
                width: 100%;
            }
            .submit-btn:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="sidebar">
                <jsp:include page="../home/sidebar.jsp"/>     
            </div>

            <div class="content">
                <div class="invite-form">
                    <h2 style="font-size: 18px; color: #333; margin-bottom: 20px;">Invite employees</h2>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" id="email" name="email" placeholder="Enter email" required>
                    </div>
                    <div class="form-group">
                        <label for="firstName">First name</label>
                        <input type="text" id="firstName" name="firstName" placeholder="Enter first name" required>
                    </div>
                    <div class="form-group">
                        <label for="lastName">Last name</label>
                        <input type="text" id="lastName" name="lastName" placeholder="Enter last name" required>
                    </div>
                    <div class="form-group">
                        <label for="employmentDate">Employment date</label>
                        <input type="date" id="employmentDate" name="employmentDate" value="2025-03-05" required>
                    </div>
                    <div class="form-group radio-group">
                        <label>Send invitation email</label>
                        <input type="radio" id="sendYes" name="sendInvitation" value="yes" checked>
                        <label for="sendYes">Yes</label>
                        <input type="radio" id="sendNo" name="sendInvitation" value="no">
                        <label for="sendNo">No</label>
                    </div>
                    <div class="form-group">
                        <label for="message">Message</label>
                        <input type="text" id="message" name="message" placeholder="Write a message (optional)">
                    </div>
                    <button type="submit" class="submit-btn">Continue to invite</button>
                </div>
            </div>
        </div>

        <script>
            // Optional: Add JavaScript for form validation or dynamic behavior
            document.querySelector('.submit-btn').addEventListener('click', function (e) {
                e.preventDefault();
                const email = document.getElementById('email').value;
                const firstName = document.getElementById('firstName').value;
                const lastName = document.getElementById('lastName').value;
                if (email && firstName && lastName) {
                    alert('Form submitted successfully!');
                    // Here you can add AJAX or form submission logic
                } else {
                    alert('Please fill in all required fields.');
                }
            });
        </script>

    </body>
</html>