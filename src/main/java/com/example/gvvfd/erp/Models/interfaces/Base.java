package com.example.gvvfd.erp.Models.interfaces;

import java.util.Date;
public interface Base {
    Date getCreated();
    void setCreated(Date created);

    Long getId();
    void setId(Long id);

    Long getAgencyId();
    void setAgencyId(Long AgencyId);

    Boolean getActive();
    void setActive(Boolean active);
}
