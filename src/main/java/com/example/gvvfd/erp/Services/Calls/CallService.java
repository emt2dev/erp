package com.example.gvvfd.erp.Services.Calls;


import com.example.gvvfd.erp.DTOs.CallDTO;
import com.example.gvvfd.erp.DTOs.CallUpdateDTO;
import com.example.gvvfd.erp.Models.Call;

import java.util.List;

public interface CallService {
    List<Call> getCallHistoryByAgencyId(Long agencyId);
    List<Call> getActiveCallsByAgencyId(Long agencyId);
    List<Call> getShiftCalls(Long agencyId, Long shiftId);
    void createCall(CallDTO Incoming);
    Call GetByIdAndAgencyId(Long Id, Long agencyId);
    void updateCall(CallUpdateDTO DTO, Call Obj, Boolean isComplete);
}
