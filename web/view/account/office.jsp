<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Office</title>
    <style>
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
        .office-form {
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
        .form-group input, .form-group select {
            width: 100%;
            padding: 8px;
            border: 1px solid #ced4da;
            border-radius: 4px;
            font-size: 14px;
            box-sizing: border-box;
            color: #6c757d;
        }
        .form-group select {
            appearance: none;
            background-color: #fff;
            background-image: url('data:image/svg+xml;utf8,<svg fill="%236c757d" height="24" viewBox="0 0 24 24" width="24" xmlns="http://www.w3.org/2000/svg"><path d="M7 10l5 5 5-5z"/></svg>');
            background-repeat: no-repeat;
            background-position: right 8px center;
            padding-right: 30px;
        }
        .form-group textarea {
            width: 100%;
            padding: 8px;
            border: 1px solid #ced4da;
            border-radius: 4px;
            font-size: 14px;
            box-sizing: border-box;
            resize: vertical;
            min-height: 80px;
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
    <jsp:include page="../home/sidebar.jsp" />

    <div class="content">
        <div class="office-form">
            <h2 style="font-size: 18px; color: #333; margin-bottom: 20px;">Add office</h2>
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" id="name" name="name" placeholder="Enter office name" required>
            </div>
            <div class="form-group">
                <label for="manager">Manager</label>
                <select id="manager" name="manager" required>
                    <option value="">Select manager</option>
                    <option value="manager1">Manager 1</option>
                    <option value="manager2">Manager 2</option>
                    <!-- Add more options as needed -->
                </select>
            </div>
            <div class="form-group">
                <label for="country">Country</label>
                <select id="country" name="country" required>
                    <option value="">Select country</option>
                    <option value="vietnam" selected>Vietnam</option>
                    <option value="usa">USA</option>
                    <!-- Add more options as needed -->
                </select>
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <textarea id="description" name="description" placeholder="Enter description"></textarea>
            </div>
            <button type="submit" class="submit-btn">Add office</button>
        </div>
    </div>

    <script>
        // Optional: Add JavaScript for form validation or dynamic behavior
        document.querySelector('.submit-btn').addEventListener('click', function(e) {
            e.preventDefault();
            const name = document.getElementById('name').value;
            const manager = document.getElementById('manager').value;
            const country = document.getElementById('country').value;
            if (name && manager && country) {
                alert('Office added successfully!');
                // Here you can add AJAX or form submission logic
            } else {
                alert('Please fill in all required fields.');
            }
        });
    </script>
</body>
</html>