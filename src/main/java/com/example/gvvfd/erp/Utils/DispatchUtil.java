package com.example.gvvfd.erp.Utils;

import com.example.gvvfd.erp.DTOs.CallAssignmentDTO;
import com.example.gvvfd.erp.DTOs.CallDTO;
import com.example.gvvfd.erp.DTOs.ShiftRosterDTO;
import com.example.gvvfd.erp.Models.Call;
import com.example.gvvfd.erp.Models.CallAssignment;
import com.example.gvvfd.erp.Models.ShiftRoster;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DispatchUtil {

    public static ShiftRoster convertToShiftRoster(ShiftRosterDTO dto, Long agencyId, Long shiftId) {
        ShiftRoster shiftRoster = new ShiftRoster();

        // Set the fields that are present in the DTO
        shiftRoster.setRosterMemberId(dto.getRosterMemberId());
        shiftRoster.setIsCommand(dto.getIsCommand());
        shiftRoster.setIsOfficer(dto.getIsOfficer());
        shiftRoster.setIsMedical(dto.getIsMedical());
        shiftRoster.setIsProbationary(dto.getIsProbationary());
        shiftRoster.setUnitCallSign(dto.getUnitCallSign());
        shiftRoster.setVehicleCallSign(dto.getVehicleCallSign());

        // Set the fields that are not in the DTO but are in the POJO
        shiftRoster.setAgencyId(agencyId);
        shiftRoster.setShiftId(shiftId);
        shiftRoster.setActive(true); // Defaulting to active, adjust as needed
        shiftRoster.setCreated(new Date()); // Setting the created date to current date

        return shiftRoster;
    }

    // Convert Call entity to CallDTO
    public static CallDTO toCallDTO(Call call) {
        List<CallAssignmentDTO> callAssignmentDTOs = call.getCallAssignments().stream()
                .map(DispatchUtil::toCallAssignmentDTO)
                .collect(Collectors.toList());

        return new CallDTO(
                call.getId(),
                call.getAgencyId(),
                call.getCreated(),
                call.getActive(),
                call.getType(),
                call.getCreatedByUserId(),
                call.getCadDisplay(),
                callAssignmentDTOs
        );
    }

    // Convert CallAssignment entity to CallAssignmentDTO
    public static CallAssignmentDTO toCallAssignmentDTO(CallAssignment callAssignment) {
        return new CallAssignmentDTO(
                callAssignment.getId(),
                callAssignment.getAgencyId(),
                callAssignment.getCreated(),
                callAssignment.getActive(),
                callAssignment.getVehicle(),
                callAssignment.getCrewCount()
        );
    }

    // Convert CallDTO to Call entity
    public static Call toCallEntity(CallDTO callDTO) {
        // Convert the list of CallAssignmentDTOs to a list of CallAssignments
        List<CallAssignment> callAssignments = callDTO.getCallAssignments().stream()
                .map(DispatchUtil::toCallAssignmentEntity)
                .collect(Collectors.toList());

        Call call = new Call();
        call.setId(callDTO.getId());
        call.setAgencyId(callDTO.getAgencyId());
        call.setCreated(callDTO.getCreated());
        call.setActive(callDTO.getActive());
        call.setType(callDTO.getType());
        call.setLocation(callDTO.getLocation());
        call.setCreatedByUserId(callDTO.getCreatedByUserId());
        call.setCadDisplay(callDTO.getCadDisplay());
        call.setCallAssignments(callAssignments); // Set the list of CallAssignments

        return call;
    }

    // Convert CallAssignmentDTO to CallAssignment entity
    public static CallAssignment toCallAssignmentEntity(CallAssignmentDTO callAssignmentDTO) {
        CallAssignment callAssignment = new CallAssignment();
        callAssignment.setId(callAssignmentDTO.getId());
        callAssignment.setAgencyId(callAssignmentDTO.getAgencyId());
        callAssignment.setCreated(callAssignmentDTO.getCreated());
        callAssignment.setActive(callAssignmentDTO.getActive());
        callAssignment.setVehicle(callAssignmentDTO.getVehicle());
        callAssignment.setCrewCount(callAssignmentDTO.getCrewCount());

        return callAssignment;
    }
}
