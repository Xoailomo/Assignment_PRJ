/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LeaveManagementSystem.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

/**
 *
 * @author phank
 */
@Getter
@Setter
public class LoginDTO {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

}
