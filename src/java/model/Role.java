/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author phank
 */
public class Role {

    private int id;
    private String name;
    private String Description;
//
//    public Role(int id, String name) {
//        this.id = id;
//        this.name = name;
//    }

    public String getDescription() {
        return Description;
    }

    // Getters và setters
    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
