package Models.interfaces;

import java.util.Date;
public interface Base {
    Date getCreated();
    void setCreated(Date created);

    Long getId();
    void setId(Long id);

    Boolean getActive();
    void setActive(Boolean active);
}
