package com.example.gvvfd.erp.DTOs;

import com.example.gvvfd.erp.Models.enums.CallType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CallDTO {
    private Long id;
    private Long agencyId;
    private Long shiftId;
    private Date created;
    private Boolean active;
    private CallType type;
    private String location;
    private Long createdByUserId;
    private Date dispatchedAt;
    private String cadDisplay;
    private List<CallAssignmentDTO> callAssignments;

    // Constructor
    public CallDTO(Long id, Long agencyId, Date created, Boolean active, CallType type,
                   Long createdByUserId, String cadDisplay, List<CallAssignmentDTO> callAssignments, Date dispatchedAt) {
        this.id = id;
        this.agencyId = agencyId;
        this.created = created;
        this.active = active;
        this.dispatchedAt = null;
        this.type = type;
        this.createdByUserId = createdByUserId;
        this.cadDisplay = cadDisplay;
        this.callAssignments = callAssignments;
    }
}
