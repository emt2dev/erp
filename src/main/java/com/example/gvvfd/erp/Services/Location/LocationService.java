package com.example.gvvfd.erp.Services.Location;

import com.example.gvvfd.erp.DTOs.LocationDTO;
import com.example.gvvfd.erp.Models.Location;

import java.util.List;

public interface LocationService {
    List<Location> GetAllByAgencyId(Long AgencyId);
    Boolean CreateLocation(LocationDTO DTO, Long AgencyId);
    Boolean UpdateLocation(Location DTO, Long LocationId);
}
