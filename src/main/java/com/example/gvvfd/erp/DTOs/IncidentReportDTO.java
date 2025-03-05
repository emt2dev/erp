package com.example.gvvfd.erp.DTOs;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class IncidentReportDTO {
    @Column(name="Description", columnDefinition = "TEXT", nullable = false)
    public String description;
    @ElementCollection
    @CollectionTable(name = "incident_report_members", joinColumns = @JoinColumn(name = "incident_report_id"))
    @Column(name = "roster_member_id")
    private List<Long> membersInvolved;  // Storing only the IDs of members

    public String resolution;
}


//{
//  "created": "2025-03-05T10:30:00Z",
//  "agencyId": 123,
//  "shiftId": 456,
//  "active": true,
//  "description": "There was a fire in the warehouse causing significant damage.",
//  "membersInvolved": [101, 102, 103],
//  "reportedById": 999,
//  "resolution": "The fire was contained, and no casualties were reported."
//}