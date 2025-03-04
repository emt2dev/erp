package com.example.gvvfd.erp.Services.RosterMembers;

import com.example.gvvfd.erp.Models.RosterMember;
import com.example.gvvfd.erp.Repositories.RosterMembersRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class RosterMembersServiceImpl implements RosterMembersService{

    @Autowired
    private RosterMembersRepository rosterMembersRepository; // Autowired to save RosterMember objects

    @PostConstruct
    public void init() {
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
        rosterMembersRepository.save(rosterMember); // Save the RosterMember object
    }
}
