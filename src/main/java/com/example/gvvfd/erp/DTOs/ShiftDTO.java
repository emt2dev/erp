package com.example.gvvfd.erp.DTOs;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ShiftDTO {
    private Boolean active;
    private Byte rosterCount;
    private Byte callCount;

    private Long commanderRosterId;
    private Long hostRosterId;

    private Date start;
    private Date end;

    private List<ShiftRosterDTO> shiftAssignments; // Assignments

}