package model;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private Department department;
    private Integer managerId; // Sử dụng Integer để cho phép NULL (nhân viên cấp cao nhất không có quản lý)

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return username; }
    public void setUserName(String name) { this.username = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
    public Integer getManagerId() { return managerId; } // Getter trả về Integer
    public void setManagerId(Integer managerId) { this.managerId = managerId; } // Setter nhận Integer
}