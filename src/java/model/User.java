/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author sonnt-local
 */
public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private Department department;
    

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
}
