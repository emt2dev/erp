package com.example.gvvfd.erp.Controllers;

import com.example.gvvfd.erp.Models.Agency;
import com.example.gvvfd.erp.Services.Agencies.AgencyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/info")
@AllArgsConstructor
public class InfoController {
    @Autowired
    private final AgencyService agencyService;

    @GetMapping("/agencies/all")
    public ResponseEntity<List<Agency>> GetAllAgencies() {
        try {
            List<Agency> All = agencyService.GetAll();
            return ResponseEntity.ok(All);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/agency/details/{agencyId}/")
    public ResponseEntity<Agency> GetDetails(@PathVariable Long agencyId) {
        try {
            // Ensure the agency exists
            Optional<Agency> a = agencyService.findById(agencyId);
            return a.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());
        }
         catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
