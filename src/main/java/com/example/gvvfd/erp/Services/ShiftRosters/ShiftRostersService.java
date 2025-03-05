package com.example.gvvfd.erp.Services.ShiftRosters;

import com.example.gvvfd.erp.DTOs.ShiftRosterDTO;

public interface ShiftRostersService {
    Boolean CreateOrUpdate(ShiftRosterDTO DTO, Boolean isNew, Long agencyId, Long shiftId);
}
