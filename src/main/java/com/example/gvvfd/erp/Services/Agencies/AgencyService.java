package com.example.gvvfd.erp.Services.Agencies;

import com.example.gvvfd.erp.Models.Agency;

import java.util.Optional;

public interface AgencyService {
    Optional<Agency> findById(Long agencyId);
}
