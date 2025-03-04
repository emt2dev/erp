package Models;

import Models.enums.CallType;
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
    private Long Id;
    private Boolean Active;
    private CallType Type;
    private Long CreatedByUserId;
    private String CADDisplay;
}
