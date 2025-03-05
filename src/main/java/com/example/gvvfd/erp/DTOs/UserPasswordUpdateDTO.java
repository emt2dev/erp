package com.example.gvvfd.erp.DTOs;

import lombok.Data;

@Data
public class UserPasswordUpdateDTO {
    private String username;
    private String oldPassword;
    private String newPassword;
}
