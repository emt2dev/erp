package com.example.gvvfd.erp.Controllers;

import com.example.gvvfd.erp.DTOs.NewUserDTO;
import com.example.gvvfd.erp.Models.Agency;
import com.example.gvvfd.erp.Services.Agencies.AgencyService;
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
@RequestMapping("/api/command")
public class CommandController {

    @Autowired
    private final AgencyService agencyService;

    @Autowired
    private final UserService userService;

    @PostMapping("/{agencyId}/user/new")
    public ResponseEntity<Void> NewUser(@PathVariable Long agencyId, @RequestBody NewUserDTO DTO) {

        try {
            // Ensure the agency exists
            Optional<Agency> a = agencyService.findById(agencyId);
            if (a.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            if (userService.CreateNewUser(DTO)) {
                return ResponseEntity.ok().build();  // Return 200 OK if the user was created successfully
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();  // Return 400 if the user creation failed
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
