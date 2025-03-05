package com.example.gvvfd.erp.Models;

import com.example.gvvfd.erp.Models.interfaces.Base;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name="IncidentReports")
@NoArgsConstructor
@AllArgsConstructor
public class IncidentReport implements Base {
    private Date created;
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long agencyId;
    private Long shiftId;
    private Boolean active;
    @Column(name="Description", columnDefinition = "TEXT", nullable = false)
    public String description;
    @ElementCollection
    @CollectionTable(name = "incident_report_members", joinColumns = @JoinColumn(name = "incident_report_id"))
    @Column(name = "roster_member_id")
    private List<Long> membersInvolved;  // Storing only the IDs of members

    @Column(name = "reported_by_id")  // Store only the ID of the person who reported
    private Long reportedById;

    public String resolution;
}
