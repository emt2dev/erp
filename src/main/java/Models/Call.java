package Models;

import Models.enums.CallType;
import Models.interfaces.Base;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "calls")  // Optional: specify a table name if it's different from class name
public class Call implements Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created", nullable = false)
    private Date created;

    @Column(name = "active")
    private Boolean active;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private CallType type;

    @Column(name = "created_by_user_id")
    private Long createdByUserId;

    @Column(name = "cad_display")
    private String cadDisplay;

    // Getters and Setters for each field
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public CallType getType() {
        return type;
    }

    public void setType(CallType type) {
        this.type = type;
    }

    public Long getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(Long createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public String getCadDisplay() {
        return cadDisplay;
    }

    public void setCadDisplay(String cadDisplay) {
        this.cadDisplay = cadDisplay;
    }
}