package com.example.gvvfd.erp.Controllers;

import com.example.gvvfd.erp.DTOs.ShiftDTO;
import com.example.gvvfd.erp.Models.Shift;
import com.example.gvvfd.erp.Services.Shifts.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/shifts")
public class ShiftController extends BaseController {

    @Autowired
    private ShiftService svc;

    // Endpoint to create a new Shift with Assignments
    @PostMapping("/create")
    public ResponseEntity<Shift> createShift(@RequestBody ShiftDTO Incoming) {
        Shift shift = svc.saveShiftWithAssignments(Incoming);
        return new ResponseEntity<>(shift, HttpStatus.CREATED);
    }
}