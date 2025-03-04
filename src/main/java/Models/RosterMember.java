package Models;

import Models.interfaces.RosterBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name="RosterMembers")
public class RosterMember implements RosterBase {
    private Date Created;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Boolean Active;
    private Boolean IsCommand;
    private Boolean IsOfficer;
    private Boolean IsMedical;
    private Boolean IsProbationary;
    private String Rank;
    private String UnitCallSign;
    private Date MostRecentShiftDate;
    private String DiscordName;
}
