package com.example.gvvfd.erp.Services.ShiftRosters;

import com.example.gvvfd.erp.Models.ShiftRoster;
import com.example.gvvfd.erp.Repositories.ShiftRostersRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ShiftRostersServiceImpl implements ShiftRostersService {
    @Autowired
    private ShiftRostersRepository repo;

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
        repo.save(sr);
    }
}
