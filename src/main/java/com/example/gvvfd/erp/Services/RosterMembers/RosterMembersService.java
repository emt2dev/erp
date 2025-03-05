package com.example.gvvfd.erp.Services.RosterMembers;

import com.example.gvvfd.erp.DTOs.RosterMemberDTO;
import com.example.gvvfd.erp.Models.RosterMember;

import java.util.List;
import java.util.Optional;

public interface RosterMembersService {
    Optional<List<RosterMember>> GetAllFromAgency(Long agencyId);
    Optional<RosterMember> GetOneByIdAndAgencyId(Long Id, Long agencyId);
    Optional<RosterMember> GetOneByUnitCallSignAndAgencyId(String callSign, Long agencyId, Boolean isCallSign);
    void Create(RosterMemberDTO Incoming);
    void Update(RosterMember Incoming);
    List<RosterMemberDTO> convertToDTOList(List<RosterMember> rosterMembers);
    RosterMemberDTO convertToDTO(RosterMember rosterMember);
    RosterMember convertToEntity(RosterMemberDTO dto);
}
