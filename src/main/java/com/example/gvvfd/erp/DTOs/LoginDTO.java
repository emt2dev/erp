package com.example.gvvfd.erp.DTOs;

import lombok.Data;

@Data
public class LoginDTO {
    private String username;
    private String password;
    private Long agencyId;
}
