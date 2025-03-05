package com.example.gvvfd.erp.DTOs;

import lombok.Data;
import java.util.Date;

@Data
public class CallAssignmentDTO {

    private Long id;
    private Long agencyId;
    private Date created;
    private Boolean active;
    private String vehicle;
    private Byte crewCount;

    // Constructor
    public CallAssignmentDTO(Long id, Long agencyId, Date created, Boolean active, String vehicle, Byte crewCount) {
        this.id = id;
        this.agencyId = agencyId;
        this.created = created;
        this.active = active;
        this.vehicle = vehicle;
        this.crewCount = crewCount;
    }
}
