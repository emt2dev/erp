package com.example.gvvfd.erp.Services.Location;

import com.example.gvvfd.erp.DTOs.LocationDTO;
import com.example.gvvfd.erp.Models.Location;
import com.example.gvvfd.erp.Repositories.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationRepository repo;

    public List<Location> GetAllByAgencyId(Long AgencyId) {
        return repo.findByAgencyId(AgencyId).orElse(Collections.emptyList());
    }

    public Boolean CreateLocation(LocationDTO DTO, Long AgencyId) {
        Location New = new Location();
        New.setAgencyId(AgencyId);
        New.setCreated(new Date());
        New.setActive(true);
        New.setCoordinates(DTO.getCoordinates());
        New.setName(DTO.getName());

        repo.saveAndFlush(New);

        return true;
    }

    public Boolean UpdateLocation(Location DTO, Long LocationId) {
        Optional<Location> Exists = repo.findById(LocationId);
        if (Exists.isPresent()) {
            Location obj = Exists.get();
            obj.setCoordinates(DTO.getCoordinates());
            obj.setName(DTO.getName());

            repo.saveAndFlush(obj);

            return true;
        }
        return false;
    }
}
