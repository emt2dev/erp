package com.example.gvvfd.erp.Services.Agencies;

import com.example.gvvfd.erp.Models.Agency;

import java.util.List;
import java.util.Optional;

public interface AgencyService {
    Optional<Agency> findById(Long agencyId);
    void Update(Agency Obj);
    List<Agency> GetAll();
}
