package com.example.gvvfd.erp.Services.IncidentReports;

import com.example.gvvfd.erp.DTOs.IncidentReportDTO;
import com.example.gvvfd.erp.Models.IncidentReport;
import com.example.gvvfd.erp.Repositories.IncidentReportsRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IncidentReportsServiceImpl implements IncidentReportsService {

    @Autowired
    private IncidentReportsRepository incidentReportsRepository; // Autowired to save IncidentReport objects

    public Boolean Create(Long agencyId, Long shiftId, Long reportedById, IncidentReportDTO DTO) {
        IncidentReport New = new IncidentReport();

        New.setShiftId(shiftId);
        New.setAgencyId(agencyId);
        New.setCreated(new Date());  // Set created date
        New.setActive(true);         // Set active status
        New.setDescription(DTO.description);  // Set description
        New.setReportedById(reportedById);    // Example reported by user ID
        New.setResolution(DTO.resolution);  // Set resolution
        New.setMembersInvolved(DTO.getMembersInvolved());

        incidentReportsRepository.saveAndFlush(New);

        return true;
    }

    public Boolean Update(Long agencyId, Long shiftId, Long incidentReportId, IncidentReportDTO DTO, Boolean isResolved) {
        Optional<IncidentReport> Report = incidentReportsRepository.findById(incidentReportId);
        if (Report.isPresent()) {
            IncidentReport Exists = Report.get();
            if (Exists.getShiftId() == shiftId && Exists.getAgencyId() == agencyId) {
                Exists.setDescription(DTO.description);  // Set description
                Exists.setMembersInvolved(DTO.getMembersInvolved());
                Exists.setResolution(DTO.resolution);  // Set resolution
            }

            if (isResolved)
                Exists.setActive(false);

            incidentReportsRepository.saveAndFlush(Exists);

            return true;
        }

        return false;
    }

    @PostConstruct
    public void init() {
        // Create an IncidentReport instance
        IncidentReport incidentReport = new IncidentReport();
        incidentReport.setAgencyId(1L);
        incidentReport.setCreated(new Date());  // Set created date
        incidentReport.setActive(true);         // Set active status
        incidentReport.setDescription("Sample incident description.");  // Set description
        incidentReport.setReportedById(1L);    // Example reported by user ID
        incidentReport.setResolution("Incident resolved successfully.");  // Set resolution

        // Create a list of members involved (you may use member IDs from your system)
        List<Long> membersInvolved = new ArrayList<>();
        membersInvolved.add(101L);  // Example member IDs
        membersInvolved.add(102L);
        membersInvolved.add(103L);

        incidentReport.setMembersInvolved(membersInvolved);  // Set members involved in the incident

        // Save the IncidentReport instance
        incidentReportsRepository.saveAndFlush(incidentReport); // Save the IncidentReport object
    }
}
