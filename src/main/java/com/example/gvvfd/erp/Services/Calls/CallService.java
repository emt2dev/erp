package com.example.gvvfd.erp.Services.Calls;


import com.example.gvvfd.erp.Models.Call;

import java.util.List;

public interface CallService {
    List<Call> getActiveCallsByAgencyId(Long agencyId);
}
