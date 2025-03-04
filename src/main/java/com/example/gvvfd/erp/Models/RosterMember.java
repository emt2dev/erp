package com.example.gvvfd.erp.Models;

import com.example.gvvfd.erp.Models.interfaces.RosterBase;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="RosterMembers")
public class RosterMember implements RosterBase {
    private Date Created;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Long agencyId;
    private Boolean Active;
    private Boolean IsCommand;
    private Boolean IsOfficer;
    private Boolean IsMedical;
    private Boolean IsProbationary;
    @Column(name="rp_rank", length=40)
    private String RpRank;
    @Column(name="unit_call_sign", length=8)
    private String UnitCallSign;
    private Date MostRecentShiftDate;
    private String DiscordName;
}
