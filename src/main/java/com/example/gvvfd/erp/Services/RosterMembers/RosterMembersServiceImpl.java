package com.example.gvvfd.erp.Services.RosterMembers;

import com.example.gvvfd.erp.DTOs.RosterMemberDTO;
import com.example.gvvfd.erp.Models.RosterMember;
import com.example.gvvfd.erp.Repositories.RosterMembersRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RosterMembersServiceImpl implements RosterMembersService {

    @Autowired
    private RosterMembersRepository rosterMembersRepository; // Autowired to save RosterMember objects

    @Override
    public void Create(RosterMemberDTO Incoming) {
        RosterMember New = convertToEntity(Incoming);
        rosterMembersRepository.saveAndFlush(New);
    }

    // Convert RosterMemberDTO to RosterMember entity
    public RosterMember convertToEntity(RosterMemberDTO dto) {
        RosterMember rosterMember = new RosterMember();
        rosterMember.setId(dto.getId()); // Assuming the entity already exists and ID is provided
        rosterMember.setAgencyId(dto.getAgencyId());
        rosterMember.setActive(dto.getActive());
        rosterMember.setIsCommand(dto.getIsCommand());
        rosterMember.setIsOfficer(dto.getIsOfficer());
        rosterMember.setIsMedical(dto.getIsMedical());
        rosterMember.setIsProbationary(dto.getIsProbationary());
        rosterMember.setRpRank(dto.getRpRank());
        rosterMember.setUnitCallSign(dto.getUnitCallSign());
        rosterMember.setMostRecentShiftDate(dto.getMostRecentShiftDate());
        rosterMember.setDiscordName(dto.getDiscordName());
        return rosterMember;
    }

    public RosterMemberDTO convertToDTO(RosterMember rosterMember) {
        RosterMemberDTO dto = new RosterMemberDTO();
        dto.setId(rosterMember.getId());
        dto.setAgencyId(rosterMember.getAgencyId());
        dto.setActive(rosterMember.getActive());
        dto.setIsCommand(rosterMember.getIsCommand());
        dto.setIsOfficer(rosterMember.getIsOfficer());
        dto.setIsMedical(rosterMember.getIsMedical());
        dto.setIsProbationary(rosterMember.getIsProbationary());
        dto.setRpRank(rosterMember.getRpRank());
        dto.setUnitCallSign(rosterMember.getUnitCallSign());
        dto.setMostRecentShiftDate(rosterMember.getMostRecentShiftDate());
        dto.setDiscordName(rosterMember.getDiscordName());
        return dto;
    }

    public List<RosterMemberDTO> convertToDTOList(List<RosterMember> rosterMembers) {
        return rosterMembers.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<List<RosterMember>> GetAllFromAgency(Long agencyId) {
        return rosterMembersRepository.findByAgencyId(agencyId);
    }

    public Optional<RosterMember> GetOneByIdAndAgencyId(Long Id, Long agencyId) {
        return rosterMembersRepository.findByIdAndAgencyId(Id, agencyId);
    }

    public void Update(RosterMember Incoming) {
        rosterMembersRepository.saveAndFlush(Incoming);
    }

    public Optional<RosterMember> GetOneByUnitCallSignAndAgencyId(String callSign, Long agencyId, Boolean isCallSign) {
        if (isCallSign) {
            // If the "isCallSign" flag is true, match by CallSign and AgencyId
            return rosterMembersRepository.findByUnitCallSignAndAgencyId(callSign, agencyId);
        } else {
            // get by discordName
            return rosterMembersRepository.findByDiscordNameAndAgencyId(callSign, agencyId);
        }
    }

    @PostConstruct
    public void init() {
        Optional<RosterMember> Exists = GetOneByUnitCallSignAndAgencyId("discordUser123", 1L, false);
        if (Exists.isEmpty()) {
            // Create a RosterMember instance
            RosterMember rosterMember = new RosterMember();
            rosterMember.setAgencyId(1L);
            rosterMember.setCreated(new Date());  // Set created date
            rosterMember.setActive(true);         // Set active status
            rosterMember.setIsCommand(true);      // Set IsCommand status
            rosterMember.setIsOfficer(false);    // Set IsOfficer status
            rosterMember.setIsMedical(true);     // Set IsMedical status
            rosterMember.setIsProbationary(false); // Set IsProbationary status
            rosterMember.setRpRank("Captain");   // Set rank (example)
            rosterMember.setUnitCallSign("1146"); // Set unit call sign (example)
            rosterMember.setMostRecentShiftDate(new Date()); // Set the most recent shift date
            rosterMember.setDiscordName("discordUser123"); // Set Discord username (example)

            // Save the RosterMember instance
            rosterMembersRepository.saveAndFlush(rosterMember); // Save the RosterMember object
        }
    }
}
