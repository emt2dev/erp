package com.example.gvvfd.erp.Models;

import com.example.gvvfd.erp.Models.interfaces.RosterBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name="RosterMembers")
@NoArgsConstructor
@AllArgsConstructor
public class RosterMember implements RosterBase {
    private Date created;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long agencyId;
    private Boolean active;
    private Boolean isCommand;
    private Boolean isOfficer;
    private Boolean isMedical;
    private Boolean isProbationary;
    @Column(name="rp_rank", length=40)
    private String rpRank;
    @Column(name="unit_call_sign", length=8)
    private String unitCallSign;
    private Date mostRecentShiftDate;
    private String discordName;
}
