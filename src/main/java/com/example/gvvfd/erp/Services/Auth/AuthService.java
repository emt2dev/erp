package com.example.gvvfd.erp.Services.Auth;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;

public interface AuthService {
    HashMap<Boolean, String> authenticate(String username, String password, Long agencyId);
}
