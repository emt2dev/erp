package com.example.gvvfd.erp.Controllers;

import com.example.gvvfd.erp.Models.Agency;
import com.example.gvvfd.erp.Models.Call;
import com.example.gvvfd.erp.Services.Agencies.AgencyService;
import com.example.gvvfd.erp.Services.Calls.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@RequestMapping("/api/dispatch")
public class DispatchController extends BaseController {
    @Autowired
    private CallService callService;

    @Autowired
    private AgencyService agencyService;

    @GetMapping("/agency/{agencyId}")
    public ResponseEntity<Call> GetNextCall(@PathVariable Long agencyId) {
        // Ensure the agency exists
        Optional<Agency> a = agencyService.findById(agencyId);
        if (a.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Get active calls by agencyId
        List<Call> activeCalls = callService.getActiveCallsByAgencyId(agencyId);
        if (activeCalls.isEmpty()) {
            return ResponseEntity.noContent().build();  // or you could return a 404 if needed
        }

        // Pick a random call from the list
        Random random = new Random();
        Call randomCall = activeCalls.get(random.nextInt(activeCalls.size()));

        return ResponseEntity.ok(randomCall);
    }
    }
