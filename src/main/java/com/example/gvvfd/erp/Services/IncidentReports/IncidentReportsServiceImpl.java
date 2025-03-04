package com.example.gvvfd.erp.Services.IncidentReports;

import com.example.gvvfd.erp.Models.IncidentReport;
import com.example.gvvfd.erp.Repositories.IncidentReportsRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IncidentReportsServiceImpl implements IncidentReportsService {

    @Autowired
    private IncidentReportsRepository incidentReportsRepository; // Autowired to save IncidentReport objects

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
        incidentReportsRepository.save(incidentReport); // Save the IncidentReport object
    }
}
