package com.example.gvvfd.erp.Services.Auth;

import com.example.gvvfd.erp.DTOs.UserPasswordUpdateDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;

public interface AuthService {
    HashMap<Boolean, String> authenticate(String username, String password, Long agencyId);
    Boolean UpdatePassword(UserPasswordUpdateDTO DTO);
    Boolean ResetPassword(Long UserId, String PasswordNew);
}
