package Models.interfaces;

import java.util.Date;

public interface RosterBase {
    Date getCreated();
    void setCreated(Date created);

    Long getId();
    void setId(Long id);

    Boolean getActive();
    void setActive(Boolean active);

    String getUnitCallSign();
    void setUnitCallSign(String val);

    Boolean IsCommand = false;
    Boolean IsOfficer = false;
    Boolean IsMedical = false;
    Boolean IsProbationary = false;

    Boolean getIsCommand();
    Boolean getIsOfficer();
    Boolean getIsMedical();
    Boolean getIsProbationary();

    void setIsCommand(Boolean val);
    void setIsOfficer(Boolean val);
    void setIsMedical(Boolean val);
    void setIsProbationary(Boolean val);
}
