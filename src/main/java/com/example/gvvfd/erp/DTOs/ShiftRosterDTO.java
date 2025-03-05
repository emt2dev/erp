package com.example.gvvfd.erp.DTOs;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ShiftRosterDTO {
    private Long id;
    private Boolean isNew;
    private Long rosterMemberId;
    private Boolean isCommand;
    private Boolean isOfficer;
    private Boolean isMedical;
    private Boolean isProbationary;
    @Column(name="unit_call_sign", length=8)
    private String unitCallSign;
    @Column(name="vehicle_call_sign", length=20)
    private String vehicleCallSign;
}
