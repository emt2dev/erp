package com.example.gvvfd.erp.Services.Calls;

import com.example.gvvfd.erp.DTOs.CallDTO;
import com.example.gvvfd.erp.DTOs.CallUpdateDTO;
import com.example.gvvfd.erp.Models.Call;
import com.example.gvvfd.erp.Models.CallAssignment;
import com.example.gvvfd.erp.Models.enums.CallType;
import com.example.gvvfd.erp.Repositories.CallAssignmentRepository;
import com.example.gvvfd.erp.Repositories.CallRepository;
import com.example.gvvfd.erp.Utils.DispatchUtil;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class CallServiceImpl implements CallService {
    @Autowired
    private final CallRepository callRepository; // Autowired to save Call objects

    @Autowired
    private final CallAssignmentRepository callAssignmentRepository; // Autowired to save CallAssignment objects

    public Call GetByIdAndAgencyId(Long Id, Long agencyId) {
        return callRepository.findByIdAndAgencyId(Id, agencyId).orElse(null);
    }

    public List<Call> getShiftCalls(Long agencyId, Long shiftId) {
        return callRepository.findByAgencyIdAndShiftId(agencyId, shiftId).orElseGet(ArrayList::new);
    }

    public List<Call> getActiveCallsByAgencyId(Long agencyId) {
        return callRepository.findByAgencyIdAndActiveTrue(agencyId).orElseGet(ArrayList::new);
    }

    public List<Call> getCallHistoryByAgencyId(Long agencyId) {
        return callRepository.findByAgencyId(agencyId).orElseGet(ArrayList::new);
    }

    public void updateCall(CallUpdateDTO DTO, Call Obj, Boolean isComplete) {
        boolean updated = false;

        if (!Obj.getLocation().equals(DTO.getLocation())) {
            Obj.setLocation(DTO.getLocation());
            updated = true;
        }

        if (!Obj.getType().equals(DTO.getType())) {
            Obj.setType(DTO.getType());
            updated = true;
        }

        if (!Obj.getCadDisplay().equals(DTO.getCadDisplay())) {
            Obj.setCadDisplay(DTO.getCadDisplay());
            updated = true;
        }

        if (isComplete) {
            Obj.setActive(false);
            updated = true;
        }

        // Only save if any updates were made
        if (updated) {
            callRepository.saveAndFlush(Obj);
        }
    }

    public void createCall(CallDTO Incoming) {
        Call New = DispatchUtil.toCallEntity(Incoming);
        callRepository.saveAndFlush(New);

        New.getCallAssignments().forEach(callAssignmentRepository::save);
    }

    @PostConstruct
    public void init() {
        // Create a Call instance
        Call call = new Call();
        call.setAgencyId(1L);
        call.setCreated(new Date());  // Set created date
        call.setActive(true);         // Set active status
        call.setType(CallType.ResidentialFire); // Set type (example value, use the actual enum type)
        call.setLocation("123131.43,4353553.44");
        call.setCreatedByUserId(1L);  // Example user ID
        call.setShiftId(1L);
        call.setCadDisplay("ENGINE 4 | LADDER 4 RESPOND TO STRUCTURE FIRE"); // Example CAD display value

        // Create a list of CallAssignments
        List<CallAssignment> callAssignments = new ArrayList<>();

        // Create 3 CallAssignment instances
        for (int i = 1; i <= 3; i++) {
            CallAssignment callAssignment = getCallAssignment(i, call);

            // Add CallAssignment to the list
            callAssignments.add(callAssignment);
        }

        // Assign the CallAssignments list to the Call object
        call.setCallAssignments(callAssignments);

        // Save the Call instance and its CallAssignments
        callRepository.saveAndFlush(call); // Save the Call object
        callAssignmentRepository.saveAll(callAssignments); // Save all the CallAssignment objects
    }

    private static CallAssignment getCallAssignment(int i, Call call) {
        CallAssignment callAssignment = new CallAssignment();
        callAssignment.setAgencyId(1L);
        callAssignment.setCreated(new Date());
        callAssignment.setActive(true);
        if (i == 1)
            callAssignment.setVehicle("Engine 4"); // Set vehicle name
        if (i == 2)
            callAssignment.setVehicle("Ladder 4"); // Set vehicle name
        if (i == 3)
            callAssignment.setVehicle("Utility 4"); // Set vehicle name
        callAssignment.setCrewCount((byte) 4); // Example crew count
        callAssignment.setCall(call); // Link the assignment to the current call
        return callAssignment;
    }
}
