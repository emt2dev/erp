package com.example.gvvfd.erp.DTOs;

import com.example.gvvfd.erp.Models.ShiftRoster;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ShiftDTO {
    private Boolean active;
    private Byte rosterCount;
    private Byte callCount;

    private Long commanderUserId;
    private Long hostUserId;

    private Date start;
    private Date end;

    private List<ShiftRosterDTO> shiftAssignments; // Assignments

    @Data
    public static class ShiftRosterDTO {
        private Long rosterMemberId;
        private Boolean isCommand;
        private Boolean isOfficer;
        private Boolean isMedical;
        private Boolean isProbationary;
        private String unitCallSign;
        private String vehicleCallSign;
        private Date start;
        private Date end;
    }
}
