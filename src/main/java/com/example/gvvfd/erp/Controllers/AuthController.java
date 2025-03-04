package com.example.gvvfd.erp.Controllers;

import com.example.gvvfd.erp.DTOs.LoginDTO;
import com.example.gvvfd.erp.Models.Agency;
import com.example.gvvfd.erp.Services.Agencies.AgencyService;
import com.example.gvvfd.erp.Services.Auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Optional;

@RequestMapping("/api/auth")
public class AuthController extends BaseController {
    @Autowired
    private AuthService authSvc;

    @Autowired
    private AgencyService agencyService;

    // Define the login route
    @PostMapping("/api/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {

        Optional<Agency> a = agencyService.findById(loginDTO.getAgencyId());
        if (a.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Call the AuthService to handle authentication
        HashMap<Boolean, String> Result = authSvc.authenticate(loginDTO.getUsername(), loginDTO.getPassword(), loginDTO.getAgencyId());

        if (Result.containsKey(true)) {
            return ResponseEntity.ok(Result.get(true));
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
