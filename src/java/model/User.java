package model;

import java.util.ArrayList;

public class User {

    private String username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getName() {
        return username;
    }

    public void setUserName(String name) {
        this.username = name;
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
