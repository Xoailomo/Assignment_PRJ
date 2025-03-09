<%-- 
    Document   : sidebar
    Created on : Mar 4, 2025, 8:25:27 PM
    Author     : phank
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    </head>
    <body>
        <div class="d-flex">
            <div calss="bg-dark text-white p-3 vh-100" id="sidebar">
                <button class="btn btn-light w-100 mb-3" type="button" data-bs-toggle="colapse" data-bs-target="menu">Menu</button>
                <div class="collapse show" id="menu">
                    <aside class="sidebar">
                        <ul class="nav flex-column">
                            <li class="nav-item">
                                <a class="nav-link text-white" href="${pageContext.request.contextPath}/view/home/create.jsp"><i class="fas fa-tachometer-alt"></i> Dashboard</a>
                            </li>
                            <li class="nav-item">
                                <a  class="nav-link text-white" href="${pageContext.request.contextPath}/view/calendar/agenda.jsp"><i class="fas fa-calendar-alt"></i> Calendar</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-white" href="${pageContext.request.contextPath}/view/leaverequest/myleaves.jsp"><i class="fas fa-umbrella-beach"></i> My Leaves</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-white" href="${pageContext.request.contextPath}/view/leaverequest/leaveApproval.jsp"><i class="fas fa-thumbs-up"></i> Leaves to approve</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-white" href="${pageContext.request.contextPath}/view/leaverequest/request_list.jsp"><i class="fas fa-chart-bar"></i> Request List</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-white" href="${pageContext.request.contextPath}/view/account/employee.jsp"><i class="fas "></i> Employees</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-white" href="${pageContext.request.contextPath}/support"><i class="fas fa-life-ring"></i> report</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-white" href="${pageContext.request.contextPath}/view/account/myAccount.jsp"><i class="fas fa-user"></i> My Account</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-white" href="${pageContext.request.contextPath}/view/auth/login.jsp"><i class="fas fa-sign-out-alt"></i> Logout</a>
                            </li>
                        </ul>
                    </aside>
                </div>
            </div>
        </div>

        <script>document.addEventListener("DOMContentLoaded", function () {
                const menuItems = document.querySelectorAll("#menu li");

                menuItems.forEach(item => {
                    item.addEventListener("click", function () {
                        // Xóa class "active" khỏi tất cả các mục
                        menuItems.forEach(i => i.classList.remove("active"));

                        // Thêm class "active" vào mục được nhấn
                        this.classList.add("active");

                        // Lưu vào localStorage để giữ trạng thái sau khi reload trang
                        localStorage.setItem("activeMenu", this.innerHTML);
                    });
                });

                // Khi load lại trang, lấy giá trị từ localStorage để duy trì trạng thái bôi xanh
                const savedActive = localStorage.getItem("activeMenu");
                if (savedActive) {
                    menuItems.forEach(item => {
                        if (item.innerHTML === savedActive) {
                            item.classList.add("active");
                        }
                    });
                }
            });
        </script>


    </body>
</html>
