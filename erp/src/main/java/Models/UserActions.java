package Models;

import Models.interfaces.Base;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name="UserActions")
public class UserActions implements Base {
    private Date Created;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private Boolean Active;
    private int RosterId;
    private Models.enums.Action Action;
    private int SubmittedUserId;
}
