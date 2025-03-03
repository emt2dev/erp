package Models;

import Models.interfaces.Base;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name="Calls")
public class Call implements Base {
    private Date Created;
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private Boolean Active;
    private int Type;
    private int CreatedByUserId;
    private String CADDisplay;
}
