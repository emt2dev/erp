package com.example.gvvfd.erp.Services.Shifts;

import com.example.gvvfd.erp.DTOs.ShiftDTO;
import com.example.gvvfd.erp.Models.Shift;

public interface ShiftService {
    Shift saveShiftWithAssignments(ShiftDTO shiftDTO);
}
