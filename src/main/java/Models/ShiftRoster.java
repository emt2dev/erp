package Models;

import Models.interfaces.RosterBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name="ShiftRosters")
public class ShiftRoster implements RosterBase {
    private Date Created;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Boolean Active;
    private Long ShiftId;
    private Long RosterMemberId;
    private Boolean IsCommand;
    private Boolean IsOfficer;
    private Boolean IsMedical;
    private Boolean IsProbationary;
    private String UnitCallSign;
    private String VehicleCallSign;
    private Date Start;
    private Date End;
}
