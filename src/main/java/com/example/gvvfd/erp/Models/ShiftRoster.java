package com.example.gvvfd.erp.Models;

import com.example.gvvfd.erp.Models.interfaces.RosterBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name="ShiftRosters")
@NoArgsConstructor
@AllArgsConstructor
public class ShiftRoster implements RosterBase {
    private Date created;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long agencyId;
    private Boolean active;
    private Long shiftId;
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
