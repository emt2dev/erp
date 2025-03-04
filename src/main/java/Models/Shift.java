package Models;

import Models.interfaces.Base;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="Shifts")
public class Shift implements Base {
    private Date Created;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Boolean Active;
    private Integer RosterCount;
    private Integer CallCount;
    private Long CommanderUserId;
    private Long HostUserId;
    private Date Start;
    private Date End;
}
