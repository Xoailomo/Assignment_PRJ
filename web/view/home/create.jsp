<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Leave Management Dashboard</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
<!--        <style>
            .input-wrapper {
                position: relative;
                margin-bottom: 15px;
            }

            .completed::after {
                content: '✔';
                color: #00cc00;
                font-size: 20px;
                position: absolute;
                right: 10px;
                top: 50%;
                transform: translateY(-50%);
            }

            .textarea-wrapper.completed::after {
                top: 20px;
            }

            .allowance {
                display: flex;
                justify-content: space-between;
                margin: 20px 0;
            }
        </style>-->
    </head>
    <body>
        <jsp:include page="/layout/header.jsp" />
        <div class="container">
            <!-- Main Content -->
            <div class="sidebar">
                <jsp:include page="../home/sidebar.jsp"/>     
            </div>
            <main class="main-content">
                <!-- Welcome Section -->
                <section class="welcome">
                    <h1>Welcome to LeaveBoard</h1>
                    <div class="links">
                        <div class="next-steps">
                            <h3>Next steps</h3>
                            <a href="../account/invite.jsp">Invite employees</a>
                            <a href="../account/office.jsp">Add an office</a>
                            <!--<a href="#">Add a team</a>-->
                        </div>
                        <!--                        <div class="more-actions">
                                                    <h3>More actions</h3>
                                                    <a href="#">Leave settings</a>
                                                    <a href="#">Company settings</a>
                                                </div>-->
                    </div>
                </section>

                <!-- Book Time Off and Balances Row -->
                <section class="book-time-off">
                    <h2>Book time off</h2>
                    <form id="leave-form" action="create" method="post">
                        <div class="input-wrapper">

                            Title:<input type="text" name="title"/> <br/>
                            Reason:<input type="text" name="reason"/> <br/>
                            From:<input type="date" name="from"/> <br/>
                            To: <input type="date" name="to"/> <br/>
                            Owner: <select name="eid">
                                <c:forEach items="${requestScope.employees}" var="e">
                                    <option value="${e.id}">${e.name}</option>
                                </c:forEach>
                            </select><br/>
                            <input type="submit" value="Create"/>
                        </div>
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

<jsp:include page="/layout/footer.jsp" />     
</body>
</html>