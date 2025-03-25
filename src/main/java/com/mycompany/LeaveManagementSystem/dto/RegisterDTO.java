/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.Date;
import lombok.*;

/**
 *
 * @author phank
 */
@Getter
@Setter
public class RegisterDTO {

    @NotEmpty
    private String firstname;
    @NotEmpty
    private String lastname;
    @NotEmpty
    private String username;
    private String phone;
    @NotEmpty
    private String email;
    @NotEmpty
    private String displayName;
    @NotEmpty
    private Date createAt;
    private String address;
    @NotEmpty
    @Size(min = 6, message = "Minimum password length is 6 characters")
    private String password;

}
