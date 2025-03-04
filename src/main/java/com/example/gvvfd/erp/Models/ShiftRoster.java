package com.example.gvvfd.erp.Models;

import com.example.gvvfd.erp.Models.interfaces.RosterBase;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="ShiftRosters")
public class ShiftRoster implements RosterBase {
    private Date Created;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Long agencyId;
    private Boolean Active;
    private Long ShiftId;
    private Long RosterMemberId;
    private Boolean IsCommand;
    private Boolean IsOfficer;
    private Boolean IsMedical;
    private Boolean IsProbationary;
    @Column(name="unit_call_sign", length=8)
    private String UnitCallSign;
    @Column(name="vehicle_call_sign", length=20)
    private String VehicleCallSign;
    private Date Start;
    private Date End;
}
