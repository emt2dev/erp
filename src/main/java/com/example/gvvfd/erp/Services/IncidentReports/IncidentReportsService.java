package com.example.gvvfd.erp.Services.IncidentReports;

import com.example.gvvfd.erp.DTOs.IncidentReportDTO;

public interface IncidentReportsService {
    Boolean Create(Long agencyId, Long shiftId, Long reportedById, IncidentReportDTO DTO);
    Boolean Update(Long agencyId, Long shiftId, Long incidentReportId, IncidentReportDTO DTO, Boolean isResolved);
}
