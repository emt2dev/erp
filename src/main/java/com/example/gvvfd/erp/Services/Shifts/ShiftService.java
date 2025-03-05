package com.example.gvvfd.erp.Services.Shifts;

import com.example.gvvfd.erp.DTOs.ShiftDTO;
import com.example.gvvfd.erp.DTOs.ShiftUpdateDTO;
import com.example.gvvfd.erp.Models.Shift;

public interface ShiftService {
    Boolean saveShiftWithAssignments(ShiftDTO shiftDTO, Long AgencyId);
    Shift getShiftByIdAndAgencyId(Long Id, Long AgencyId);
    Boolean updateShift(Long ShiftId, Long AgencyId, Boolean isActive);
}
