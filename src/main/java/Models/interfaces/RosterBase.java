package Models.interfaces;

import java.util.Date;

public interface RosterBase {
    Date getCreated();
    void setCreated(Date created);

    int getId();
    void setId(int id);

    Boolean getActive();
    void setActive(Boolean active);

    int getUnitCallSign();
    void setUnitCallSign(int val);

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
