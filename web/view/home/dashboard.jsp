<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Leave Management Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
    <div class="container">
        <!-- Sidebar -->
        <aside class="sidebar">
            <ul>
                <li class="active"><a href="#"><i class="fas fa-tachometer-alt"></i> Dashboard</a></li>
                <li><a href="#"><i class="fas fa-calendar-alt"></i> Calendar</a></li>
                <li><a href="#"><i class="fas fa-umbrella-beach"></i> My leaves</a></li>
                <li><a href="#"><i class="fas fa-check-circle"></i> Leaves to approve</a></li>
                <li><a href="#"><i class="fas fa-users"></i> Employees</a></li>
                <li><a href="#"><i class="fas fa-chart-bar"></i> Reports</a></li>
                <li><a href="#"><i class="fas fa-life-ring"></i> Support</a></li>
                <li><a href="#"><i class="fas fa-building"></i> Company</a></li>
                <li><a href="#"><i class="fas fa-user"></i> My account</a></li>
                <li><a href="#"><i class="fas fa-compress"></i> Collapse menu</a></li>
                <li class="logout"><a href="${pageContext.request.contextPath}/logout"><i class="fas fa-sign-out-alt"></i> Logout</a></li>
            </ul>
        </aside>

        <!-- Main Content -->
        <main class="main-content">
            <!-- Welcome Section -->
            <section class="welcome">
                <h1>Welcome to LeaveBoard</h1>
                <p>We have assembled some links to get you started!</p>
                <div class="links">
                    <div class="next-steps">
                        <h3>Next steps</h3>
                        <a href="#">Invite employees</a>
                        <a href="#">Add an office</a>
                        <a href="#">Add a team</a>
                    </div>
                    <div class="more-actions">
                        <h3>More actions</h3>
                        <a href="#">Leave settings</a>
                        <a href="#">Company settings</a>
                    </div>
                </div>
            </section>

            <!-- Book Time Off and Balances Row -->
            <div class="book-balance-row">
                <!-- Book Time Off Section -->
                <section class="book-time-off">
                    <h2>Book time off</h2>
                    <form id="leave-form" action="${pageContext.request.contextPath}/submitLeave" method="post">
                        <label for="leave-type">Leave type</label>
                        <select id="leave-type" name="leaveType">
                            <option value="vacation">Vacation</option>
                            <option value="sick-leave">Sick leave</option>
                            <option value="unpaid-leave">Unpaid leave</option>
                        </select>

                        <label for="duration">Duration</label>
                        <select id="duration" name="duration">
                            <option value="one-day">One day</option>
                            <option value="multiple-days">Multiple days</option>
                        </select>

                        <label for="day">Day</label>
                        <input type="date" id="day" name="day" value="2025-05-03">

                        <label for="comment">Comment</label>
                        <textarea id="comment" name="comment" rows="3"></textarea>

                        <div class="allowance">
                            <div>
                                <h3>Allowance</h3>
                                <p id="total-allowance">${leaveBalances.vacation.allowance} Days</p>
                            </div>
                            <div>
                                <h3>If approved</h3>
                                <p id="if-approved">${leaveBalances.vacation.allowance - 1} Days</p>
                            </div>
                        </div>

                        <button type="submit">Book time off <i class="fas fa-plus-circle"></i></button>
                    </form>
                </section>

                <!-- Leave Balances -->
                <section class="leave-balances">
                    <table>
                        <thead>
                            <tr>
                                <th></th>
                                <th>Balance</th>
                                <th>Used</th>
                                <th>Available</th>
                                <th>Allowance</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><span class="dot vacation"></span> Vacation</td>
                                <td></td>
                                <td>${leaveBalances.vacation.used}</td>
                                <td>${leaveBalances.vacation.available}</td>
                                <td>${leaveBalances.vacation.allowance}</td>
                            </tr>
                            <tr>
                                <td><span class="dot sick-leave"></span> Sick leave</td>
                                <td></td>
                                <td>${leaveBalances.sickLeave.used}</td>
                                <td>${leaveBalances.sickLeave.available}</td>
                                <td>${leaveBalances.sickLeave.allowance}</td>
                            </tr>
                            <tr>
                                <td><span class="dot unpaid-leave"></span> Unpaid leave</td>
                                <td></td>
                                <td>${leaveBalances.unpaidLeave.used}</td>
                                <td>${leaveBalances.unpaidLeave.available}</td>
                                <td>${leaveBalances.unpaidLeave.allowance}</td>
                            </tr>
                        </tbody>
                    </table>
                </section>
            </div>

            <!-- Work Anniversary -->
            <section class="work-anniversary">
                <h2>Work anniversary</h2>
                <p>${workAnniversary.name}</p>
                <p>${workAnniversary.employeeId}</p>
                <p>${workAnniversary.timeUntil}</p>
                <p>${workAnniversary.timeFrom}</p>
                <p class="range">Range: <fmt:formatDate value="${workAnniversary.startDate}" pattern="dd/MM/yyyy"/> - <fmt:formatDate value="${workAnniversary.endDate}" pattern="dd/MM/yyyy"/></p>
            </section>

            <!-- Agenda/Calendar -->
            <section class="agenda">
                <div class="agenda-header">
                    <h2>March 2025</h2>
                    <div class="filters">
                        <input type="text" placeholder="Name" id="name-filter">
                        <button><i class="fas fa-filter"></i> Filter</button>
                    </div>
                </div>
                <div class="agenda-table">
                    <table>
                        <thead>
                            <tr>
                                <th></th>
                                <th>Tu 4</th>
                                <th>We 5</th>
                                <th>Th 6</th>
                                <th>Fr 7</th>
                                <th>Sa 8</th>
                                <th>Su 9</th>
                                <th>Mo 10</th>
                                <th>Tu 11</th>
                                <th>We 12</th>
                                <th>Th 13</th>
                                <th>Fr 14</th>
                                <th>Sa 15</th>
                                <th>Su 16</th>
                                <th>Mo 17</th>
                                <th>Tu 18</th>
                                <th>We 19</th>
                                <th>Th 20</th>
                                <th>Fr 21</th>
                                <th>Sa 22</th>
                                <th>Su 23</th>
                                <th>Mo 24</th>
                                <th>Tu 25</th>
                                <th>We 26</th>
                                <th>Th 27</th>
                                <th>Fr 28</th>
                                <th>Sa 29</th>
                                <th>Su 30</th>
                                <th>Mo 31</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="employee" items="${agenda}">
                                <tr>
                                    <td>${employee.name}</td>
                                    <c:forEach var="dayStatus" items="${employee.days}">
                                        <td class="${dayStatus}"></td>
                                    </c:forEach>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </section>
        </main>
    </div>

    <script src="${pageContext.request.contextPath}/javascript/script.js"></script>
</body>
</html>