<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Dashboard</title>
    <style>
        body {
            font-family: 'Segoe UI', Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f5f7fa;
            display: flex;
            height: 100vh;
        }
        .content {
            flex: 1;
            padding: 20px;
            overflow-y: auto;
            margin-left: 250px;
        }
        .nav-bar {
            background-color: #fff;
            padding: 10px 20px;
            border-radius: 5px;
            margin-bottom: 20px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .nav-bar a {
            text-decoration: none;
            color: #007bff;
            margin-right: 15px;
            font-weight: 600;
            font-size: 14px;
        }
        .nav-bar a.active {
            text-decoration: underline;
            text-underline-offset: 4px;
        }
        .invite-btn {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 6px 15px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
            font-weight: 600;
        }
        .invite-btn:hover {
            background-color: #0056b3;
        }
        .table-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }
        table {
            width: 50%;
            border-collapse: collapse;
            font-size: 14px;
            align-content: right;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #dee2e6;
            color: #333;
        }
        th {
            background-color: #f8f9fa;
            font-weight: 600;
        }
        .search-bar {
            width: 50%;
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ced4da;
            border-radius: 4px;
            font-size: 14px;
        }
        .download-btn, .filter-btn {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 6px 10px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
            margin-left: 10px;
        }
        .download-btn:hover, .filter-btn:hover {
            background-color: #0056b3;
        }
        .icons {
            display: inline-block;
            width: 16px;
            height: 16px;
            background-color: #6c757d;
            margin-right: 5px;
            vertical-align: middle;
            border-radius: 2px;
        }
        .employee-icon { background-color: #007bff; }
        .approver-icon { background-color: #28a745; }
        .position-icon { background-color: #17a2b8; }
        .team-icon { background-color: #dc3545; }
        .office-icon { background-color: #ffc107; }
        .country-icon { background-color: #6c757d; }
    </style>
</head>
<body>
    <jsp:include page="../home/sidebar.jsp" />

    <div class="content">
        <div class="nav-bar">
            <div>
                <a href="#" class="active">Employees</a>
<!--                <a href="#">Teams</a>
                <a href="#">Offices</a>
                <a href="#">Reports</a>-->
            </div>
            <button class="invite-btn" onclick="window.location.href='../account/invite.jsp'">+ Invite employees</button>
        </div>

        <div class="table-container">
            <input type="text" class="search-bar" placeholder="Search">
            <button class="filter-btn">Filter <span class="icons"></span></button>

            <table>
                <thead>
                    <tr>
                        <th><span class="icons employee-icon"></span> Employee</th>
                        <th><span class="icons approver-icon"></span> Approver</th>
                        <th><span class="icons position-icon"></span> Position</th>
                        <th><span class="icons team-icon"></span> Team</th>
                        <th><span class="icons office-icon"></span> Office</th>
                        <th><span class="icons country-icon"></span> Country</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>111,111</td>
                        <td>hai, hai</td>
                        <td>IT</td>
                        <td>Vietnam</td>
                        <td>Vietnam</td>
                        <td>Vietnam</td>
                    </tr>
                    <tr>
                        <td>dfdfd, dfdfd</td>
                        <td>hai, hai</td>
                        <td>IT</td>
                        <td>Vietnam</td>
                        <td>Vietnam</td>
                        <td>Vietnam</td>
                    </tr>
                    <tr>
                        <td>hai, hai</td>
                        <td>hai, hai</td>
                        <td>IT</td>
                        <td>Vietnam</td>
                        <td>Vietnam</td>
                        <td>Vietnam</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>

    <script>
        // Simple interactivity for search functionality
        document.querySelector('.search-bar').addEventListener('input', function(e) {
            const searchTerm = e.target.value.toLowerCase();
            const rows = document.querySelectorAll('tbody tr');
            rows.forEach(row => {
                const text = row.textContent.toLowerCase();
                row.style.display = text.includes(searchTerm) ? '' : 'none';
            });
        });
    </script>
</body>
</html>