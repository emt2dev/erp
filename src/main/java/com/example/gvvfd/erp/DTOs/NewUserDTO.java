package com.example.gvvfd.erp.DTOs;

import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class NewUserDTO {
    private String username;
    private String createdByUsername;
    private String userRole;
    private Long agencyId;
}
