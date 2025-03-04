package com.example.gvvfd.erp.Services.Shifts;

import com.example.gvvfd.erp.DTOs.ShiftDTO;
import com.example.gvvfd.erp.Models.Shift;
import com.example.gvvfd.erp.Models.ShiftRoster;
import com.example.gvvfd.erp.Repositories.ShiftRepository;
import com.example.gvvfd.erp.Repositories.ShiftRostersRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShiftServiceImpl implements ShiftService {
    @Autowired
    private ShiftRepository shiftRepository;  // Autowired to save Shift entities

    @Autowired
    private ShiftRostersRepository shiftRosterRepository;  // Autowired to save ShiftRoster assignments

    @PostConstruct
    public void init() {
        // Create a Shift instance
        Shift shift = new Shift();
        shift.setAgencyId(1L);
        shift.setCreated(new Date()); // Set created date
        shift.setActive(true);        // Set active status
        shift.setRosterCount((byte) 5); // Set the number of roster members
        shift.setCallCount((byte) 2);  // Set the number of calls
        shift.setCommanderUserId(1L); // Example commander user ID
        shift.setHostUserId(2L);      // Example host user ID
        shift.setStart(new Date());   // Set the start time of the shift
        shift.setEnd(new Date());     // Set the end time of the shift

        // Save the Shift instance
        shift = shiftRepository.save(shift);

        // Create multiple ShiftRoster assignments (assign RosterMembers to vehicles)
        List<ShiftRoster> shiftRosters = new ArrayList<>();

        // Example of 3 roster members being assigned to different vehicles
        for (int i = 1; i <= 3; i++) {
            ShiftRoster shiftRoster = new ShiftRoster();
            shiftRoster.setCreated(new Date());  // Set created date for each shift roster
            shiftRoster.setActive(true);         // Set active status
            shiftRoster.setShiftId(shift.getId());  // Set the ShiftId
            shiftRoster.setRosterMemberId((long) i); // Set the RosterMember ID (1, 2, 3 in this case)
            shiftRoster.setIsCommand(i == 1);    // Assign the first member as Command
            shiftRoster.setIsOfficer(i == 2);    // Assign the second member as Officer
            shiftRoster.setIsMedical(i == 3);    // Assign the third member as Medical
            shiftRoster.setIsProbationary(false); // Example: None are probationary
            shiftRoster.setUnitCallSign("Unit" + i);  // Set unit call sign (e.g., Unit1, Unit2)
            shiftRoster.setVehicleCallSign("Vehicle" + i); // Set vehicle call sign (e.g., Vehicle1, Vehicle2)
            shiftRoster.setStart(new Date());   // Set the start time of the shift assignment
            shiftRoster.setEnd(new Date());     // Set the end time of the shift assignment

            shiftRosters.add(shiftRoster);
        }

        // Save all ShiftRoster assignments
        shiftRosterRepository.saveAll(shiftRosters);
    }



    @Transactional
    public Shift saveShiftWithAssignments(ShiftDTO shiftDTO) {
        // Create Shift entity
        Shift shift = new Shift();
        shift.setActive(shiftDTO.getActive());
        shift.setRosterCount(shiftDTO.getRosterCount());
        shift.setCallCount(shiftDTO.getCallCount());
        shift.setCommanderUserId(shiftDTO.getCommanderUserId());
        shift.setHostUserId(shiftDTO.getHostUserId());
        shift.setStart(shiftDTO.getStart());
        shift.setEnd(shiftDTO.getEnd());

        // Save Shift entity
        shift = shiftRepository.save(shift);

        // Save each ShiftAssignment (ShiftRoster)
        for (ShiftDTO.ShiftRosterDTO shiftRosterDTO : shiftDTO.getShiftAssignments()) {
            ShiftRoster shiftRoster = new ShiftRoster();
            shiftRoster.setRosterMemberId(shiftRosterDTO.getRosterMemberId());
            shiftRoster.setIsCommand(shiftRosterDTO.getIsCommand());
            shiftRoster.setIsOfficer(shiftRosterDTO.getIsOfficer());
            shiftRoster.setIsMedical(shiftRosterDTO.getIsMedical());
            shiftRoster.setIsProbationary(shiftRosterDTO.getIsProbationary());
            shiftRoster.setUnitCallSign(shiftRosterDTO.getUnitCallSign());
            shiftRoster.setVehicleCallSign(shiftRosterDTO.getVehicleCallSign());
            shiftRoster.setStart(shiftRosterDTO.getStart());
            shiftRoster.setEnd(shiftRosterDTO.getEnd());

            shiftRoster.setShiftId(shift.getId());
            // Save each ShiftRoster
            shiftRosterRepository.save(shiftRoster);
        }

        return shift;
    }
}
