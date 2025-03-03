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
    private int Id;
    private Boolean Active;
    private int ShiftId;
    private int RosterMemberId;
    private Boolean IsCommand;
    private Boolean IsOfficer;
    private Boolean IsMedical;
    private Boolean IsProbationary;
    private int UnitCallSign;
    private String VehicleCallSign;
    private Date Start;
    private Date End;
}
