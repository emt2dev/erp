package com.example.gvvfd.erp.Controllers;

import com.example.gvvfd.erp.DTOs.LoginDTO;
import com.example.gvvfd.erp.Models.Agency;
import com.example.gvvfd.erp.Services.Agencies.AgencyService;
import com.example.gvvfd.erp.Services.Auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authSvc;

    @Autowired
    private AgencyService agencyService;

    @PostMapping("/test-request")
    public ResponseEntity<String> testPostRequest() {
        return ResponseEntity.ok("POST request successful");
    }

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
