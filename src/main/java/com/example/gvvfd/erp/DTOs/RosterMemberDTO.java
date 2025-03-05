package com.example.gvvfd.erp.DTOs;

import lombok.Data;
import java.util.Date;

@Data
public class RosterMemberDTO {
    private Long id;
    private Long agencyId;
    private Boolean active;
    private Boolean isCommand;
    private Boolean isOfficer;
    private Boolean isMedical;
    private Boolean isProbationary;
    private String rpRank;
    private String unitCallSign;
    private Date mostRecentShiftDate;
    private String discordName;
}