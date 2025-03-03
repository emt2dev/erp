package Models;

import Models.interfaces.Base;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name="CallAssignments")
public class CallAssignment implements Base {
    private Date Created;
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private Boolean Active;
    private int CallId;
    private String Vehicle;
    private int CrewCount;
}
