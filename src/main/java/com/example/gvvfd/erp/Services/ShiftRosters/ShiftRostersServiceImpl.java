package com.example.gvvfd.erp.Services.ShiftRosters;

import com.example.gvvfd.erp.DTOs.ShiftRosterDTO;
import com.example.gvvfd.erp.Models.Shift;
import com.example.gvvfd.erp.Models.ShiftRoster;
import com.example.gvvfd.erp.Repositories.ShiftRostersRepository;
import com.example.gvvfd.erp.Utils.DispatchUtil;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShiftRostersServiceImpl implements ShiftRostersService {
    @Autowired
    private ShiftRostersRepository repo;

    public Boolean CreateOrUpdate(ShiftRosterDTO DTO, Boolean isNew, Long agencyId, Long shiftId) {
        if (DTO.getIsNew()) {
            ShiftRoster NewObj = DispatchUtil.convertToShiftRoster(DTO, agencyId, shiftId);

            repo.saveAndFlush(NewObj);

            return true;
        } else {
            Optional<ShiftRoster> Exists = repo.findById(DTO.getId());
            if (Exists.isPresent()) {
                ShiftRoster shiftRoster = Exists.get();
                shiftRoster.setIsCommand(DTO.getIsCommand());
                shiftRoster.setIsOfficer(DTO.getIsOfficer());
                shiftRoster.setIsMedical(DTO.getIsMedical());
                shiftRoster.setIsProbationary(DTO.getIsProbationary());
                shiftRoster.setUnitCallSign(DTO.getUnitCallSign());
                shiftRoster.setVehicleCallSign(DTO.getVehicleCallSign());

                repo.saveAndFlush(shiftRoster);

                return true;
            }
        }

        return false;
    }

    @PostConstruct
    public void init() {
        ShiftRoster sr = new ShiftRoster();
        sr.setAgencyId(1L);
        sr.setCreated(new Date());  // Set created date
        sr.setActive(true);
        sr.setRosterMemberId(1L);
        sr.setShiftId(1L);
        sr.setVehicleCallSign("Engine 4");
        sr.setUnitCallSign("1146"); // Set unit call sign (example)
        sr.setIsCommand(true);      // Set IsCommand status
        sr.setIsOfficer(false);    // Set IsOfficer status
        sr.setIsMedical(true);     // Set IsMedical status
        sr.setIsProbationary(false); // Set IsProbationary status
        repo.saveAndFlush(sr);
    }
}
