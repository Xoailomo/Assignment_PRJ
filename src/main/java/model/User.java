package model;

import java.util.ArrayList;

public class User {

    private int userId;
    private String userName;
    private String password;
    private Department department;
    private Integer managerId; // Sử dụng Integer để cho phép NULL (nhân viên cấp cao nhất không có quản lý)
    private Employee employee;
    private ArrayList<Role> roles = new ArrayList<>();
    private String displayname;

    
    
    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public ArrayList<Role> getRoles() {
        return roles;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Integer getManagerId() {
        return managerId;
    } // Getter trả về Integer

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    } // Setter nhận Integer
}
