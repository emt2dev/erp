package com.example.gvvfd.erp.Controllers;

import com.example.gvvfd.erp.DTOs.NewUserDTO;
import com.example.gvvfd.erp.DTOs.UserPasswordUpdateDTO;
import com.example.gvvfd.erp.Models.Agency;
import com.example.gvvfd.erp.Services.Agencies.AgencyService;
import com.example.gvvfd.erp.Services.Auth.AuthService;
import com.example.gvvfd.erp.Services.JWT.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/officer")
public class OfficerController {
    @Autowired
    private final AgencyService agencyService;

    @Autowired
    private final AuthService authService;

    @Autowired
    private final UserService userService;

    @PostMapping("/{agencyId}/user/update/{userId}/role/{newRole}")
    public ResponseEntity<Void> UpdateRole(@PathVariable Long agencyId, @PathVariable Long userId, @PathVariable String newRole) {
        try {
            // Ensure the agency exists
            Optional<Agency> a = agencyService.findById(agencyId);
            if (a.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            userService.UpdateRole(userId, agencyId, newRole);

            return ResponseEntity.ok().build();  // Return 200 OK if the user was created successfully
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/{agencyId}/user/update/{userId}")
    public ResponseEntity<Void> UpdatePassword(@PathVariable Long agencyId, @PathVariable Long userId, @RequestBody UserPasswordUpdateDTO DTO) {
        try {
            // Ensure the agency exists
            Optional<Agency> a = agencyService.findById(agencyId);
            if (a.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            if (authService.UpdatePassword(DTO)) {
                return ResponseEntity.ok().build();  // Return 200 OK if the user was created successfully
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();  // Return 400 if the user creation failed
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/{agencyId}/user/reset/{userId}")
    public ResponseEntity<Void> ResetPassword(@PathVariable Long agencyId, @PathVariable Long userId) {
        try {
            // Ensure the agency exists
            Optional<Agency> a = agencyService.findById(agencyId);
            if (a.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            if (authService.ResetPassword(userId, "P@ssword1")) {
                return ResponseEntity.ok().build();  // Return 200 OK if the user was created successfully
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();  // Return 400 if the user creation failed
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}