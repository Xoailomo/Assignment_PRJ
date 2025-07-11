# Employee Leave Management System

A comprehensive system for managing employee leave requests within a hierarchical organization. This platform allows employees to submit time-off requests and enables managers to review, approve, or reject them efficiently.

## 📜 Table of Contents

- [📖 Core Concepts](#-core-concepts)
- [✨ Features](#-features)
- [📊 Usecase Diagram](#-usecase-diagram)
- [⚙️ Technology Stack](#️-technology-stack)
- [🚀 Getting Started](#-getting-started)
- [📄 License](#-license)

## 📖 Core Concepts

This system is designed for a company with a clear organizational structure.

### 1. Hierarchical Structure
Employees are organized in a hierarchy. Each employee reports to a direct manager, who is responsible for approving their leave requests.

*Image: A diagram illustrating the structure of an IT department with reporting lines.*

### 2. Departments & Roles
- Every employee belongs to a specific department (e.g., IT, HR, Sales).
- Each user account is assigned one or more **Roles** (e.g., `Developer`, `Team Lead`, `Department Head`).

### 3. Role-Based Access Control (RBAC)
A user's roles determine which system **Features** they can access. For example, a user `u1` with roles `r1` and `r2` might be able to access features `f3` and `f4`, while another user cannot.

*Image: A conceptual diagram illustrating how Users, Roles, and Features are connected.*

---

## ✨ Features

The system includes the following key features:

### 1. Authentication
- Users must log in to access the system's features.
- Supports a standard username/password login form.
- Can be integrated with third-party authentication providers like Google, GitHub, etc. (Open Authentication).

### 2. Create Leave Request
- An intuitive form for employees to submit a new leave request.
- Each request includes essential information such as **Leave Type**, **Start Date**, **End Date**, and a **Reason/Description**.
- Upon creation, a request is automatically set to the **`Inprogress`** state, awaiting manager review.

#### Request State Machine
Every leave request follows a simple lifecycle:

*Image: State machine diagram showing `Inprogress` -> `Approved` / `Rejected` states.*

A request starts as `Inprogress` and can transition to either `Approved` or `Rejected`.

### 3. View Requests
Users can view lists of leave requests in a clear, tabular format. There are two primary views:

- **My Requests:** A view for an employee to see all the leave requests they have personally submitted.
- **Subordinates' Requests:** A view for managers to see all leave requests submitted by employees who report directly to them.

**Example List View:**

| Title                  | From Date  | To Date    | Created By | Status     | Processed By |
| ---------------------- | ---------- | ---------- | ---------- | ---------- | ------------ |
| Wedding Leave Request  | 2025-01-01 | 2025-01-03 | Mr. F      | Inprogress |              |
| Vacation Time          | 2025-01-01 | 2025-01-05 | Mr. E      | Rejected   | Mr. B        |
| ...                    | ...        | ...        | ...        | ...        | ...          |

### 4. Process Requests (Manager Action)
- From the "Subordinates' Requests" list, a manager can click on a request to view its full details.
- This detailed view contains buttons to **Approve** or **Reject** the request.
- The manager can (and should) provide a reason for their decision, especially for rejections.

*Image: A sample form for approving or rejecting a subordinate's leave request.*

### 5. Team Agenda View
- A special view designed for **Department Heads** or managers to get a high-level overview of their team's availability.
- It displays a calendar-like grid with employees listed in rows and dates in columns.
- Cells are color-coded to indicate status:
  - 🟩 **Green:** Employee is working.
  - 🟥 **Red:** Employee is on approved leave.

**Example Agenda View (Jan 1 - Jan 9):**

| Employee | Jan 1 | Jan 2 | Jan 3 | Jan 4 | Jan 5 | Jan 6 | Jan 7 | Jan 8 | Jan 9 |
| :------- | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| Mr. A    | 🟩    | 🟩    | 🟩    | 🟩    | 🟩    | 🟩    | 🟩    | 🟩    | 🟩    |
| Mr. B    | 🟩    | 🟩    | 🟩    | 🟩    | 🟩    | 🟩    | 🟩    | 🟩    | 🟩    |
| Mr. C    | 🟥    | 🟥    | 🟥    | 🟩    | 🟩    | 🟩    | 🟩    | 🟩    | 🟩    |
| Mr. D    | 🟩    | 🟩    | 🟩    | 🟩    | 🟥    | 🟥    | 🟥    | 🟩    | 🟩    |

---

## 📊 Usecase Diagram

The following diagram outlines the primary interactions between users (actors) and the system.

*Image: Usecase Diagram for the Employee Leave Management System.*

---

## ⚙️ Technology Stack

*(This section is a placeholder. Please update it with the technologies used in your project.)*

- **Frontend:** React, Vue, or Angular
- **Backend:** Node.js (Express), Python (Django/Flask), or Java (Spring Boot)
- **Database:** MongoDB, PostgreSQL, or MySQL
- **Authentication:** JWT (JSON Web Tokens), OAuth 2.0

---

## 🚀 Getting Started

Follow these instructions to get a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Node.js
- npm or yarn
- Git

### Installation

1.  **Clone the repository**
    ```sh
    git clone https://github.com/your-username/leave-management-system.git
    ```
2.  **Navigate to the project directory**
    ```sh
    cd leave-management-system
    ```
3.  **Install dependencies**
    ```sh
    npm install
    ```
4.  **Start the development server**
    ```sh
    npm start
    ```

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.
