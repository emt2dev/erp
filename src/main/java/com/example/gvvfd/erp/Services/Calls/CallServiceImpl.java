package com.example.gvvfd.erp.Services.Calls;

import com.example.gvvfd.erp.Models.Call;
import com.example.gvvfd.erp.Models.CallAssignment;
import com.example.gvvfd.erp.Models.enums.CallType;
import com.example.gvvfd.erp.Repositories.CallAssignmentRepository;
import com.example.gvvfd.erp.Repositories.CallRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CallServiceImpl implements CallService {
    @Autowired
    private CallRepository callRepository; // Autowired to save Call objects

    @Autowired
    private CallAssignmentRepository callAssignmentRepository; // Autowired to save CallAssignment objects

    public List<Call> getActiveCallsByAgencyId(Long agencyId) {
        return callRepository.findByAgencyIdAndActiveTrue(agencyId); // assuming the active column is a boolean
    }

    @PostConstruct
    public void init() {
        // Create a Call instance
        Call call = new Call();
        call.setAgencyId(1L);
        call.setCreated(new Date());  // Set created date
        call.setActive(true);         // Set active status
        call.setType(CallType.ResidentialFire); // Set type (example value, use the actual enum type)
        call.setCreatedByUserId(1L);  // Example user ID
        call.setCadDisplay("ENGINE 4 | LADDER 4 RESPOND TO STRUCTURE FIRE"); // Example CAD display value

        // Create a list of CallAssignments
        List<CallAssignment> callAssignments = new ArrayList<>();

        // Create 3 CallAssignment instances
        for (int i = 1; i <= 3; i++) {
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

            // Add CallAssignment to the list
            callAssignments.add(callAssignment);
        }

        // Assign the CallAssignments list to the Call object
        call.setCallAssignments(callAssignments);

        // Save the Call instance and its CallAssignments
        callRepository.save(call); // Save the Call object
        callAssignmentRepository.saveAll(callAssignments); // Save all the CallAssignment objects
    }
}
