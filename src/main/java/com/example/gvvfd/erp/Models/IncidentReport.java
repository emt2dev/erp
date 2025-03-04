package com.example.gvvfd.erp.Models;

import com.example.gvvfd.erp.Models.interfaces.Base;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name="IncidentReports")
public class IncidentReport implements Base {
    private Date Created;
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Long agencyId;
    private Boolean Active;
    @Column(name="Description", columnDefinition = "TEXT", nullable = false)
    public String Description;
    @ElementCollection
    @CollectionTable(name = "incident_report_members", joinColumns = @JoinColumn(name = "incident_report_id"))
    @Column(name = "roster_member_id")
    private List<Long> MembersInvolved;  // Storing only the IDs of members

    @Column(name = "reported_by_id")  // Store only the ID of the person who reported
    private Long reportedById;

    public String Resolution;
}
