package Models.interfaces;

import java.util.Date;
public interface Base {
    Date getCreated();
    void setCreated(Date created);

    int getId();
    void setId(int id);

    Boolean getActive();
    void setActive(Boolean active);
}
