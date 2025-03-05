package com.example.gvvfd.erp.Controllers;

import com.example.gvvfd.erp.DTOs.RosterMemberDTO;
import com.example.gvvfd.erp.Models.Agency;
import com.example.gvvfd.erp.Models.RosterMember;
import com.example.gvvfd.erp.Services.Agencies.AgencyService;
import com.example.gvvfd.erp.Services.JWT.UserService;
import com.example.gvvfd.erp.Services.RosterMembers.RosterMembersService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/dispatch")
@AllArgsConstructor
public class AgencyController {
    @Autowired
    private final AgencyService agencyService;

    @Autowired
    private final RosterMembersService rosterMembersService;

    @Autowired
    private final UserService userService;

    @PostMapping("/{agencyId}/members/new")
    public ResponseEntity<Boolean> NewMember(@PathVariable Long agencyId, @RequestBody RosterMemberDTO DTO) {


        try {
            // Ensure the agency exists
            Optional<Agency> a = agencyService.findById(agencyId);
            if (a.isEmpty())
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

            rosterMembersService.Create(DTO);

            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/{agencyId}/members/update/{rosterMemberId}")
    public ResponseEntity<Boolean> UpdateMember(@PathVariable Long agencyId, @PathVariable Long rosterMemberId, @RequestBody RosterMemberDTO Incoming) {


        try {
            // Ensure the agency exists
            Optional<Agency> a = agencyService.findById(agencyId);
            if (a.isEmpty())
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

            Optional<RosterMember> Exists = rosterMembersService.GetOneByIdAndAgencyId(rosterMemberId, agencyId);

            if (Exists.isEmpty())
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

            rosterMembersService.Update(rosterMembersService.convertToEntity(Incoming));

            return ResponseEntity.ok(true);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/{agencyId}/details/update")
    public ResponseEntity<Boolean> UpdateDetails(@PathVariable Long agencyId, @RequestBody Agency Obj) {


        try {
            // Ensure the agency exists
            Optional<Agency> a = agencyService.findById(agencyId);
            if (a.isEmpty())
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

            agencyService.Update(Obj);

            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{agencyId}/details")
    public ResponseEntity<Agency> GetDetails(@PathVariable Long agencyId) {

        try {

            // Ensure the agency exists
            Optional<Agency> a = agencyService.findById(agencyId);
            return a.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{agencyId}/members/all")
    public ResponseEntity<List<RosterMember>> GetRosterMembers(@PathVariable Long agencyId) {


        try {
            // Ensure the agency exists
            Optional<Agency> a = agencyService.findById(agencyId);
            if (a.isEmpty())
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

            Optional<List<RosterMember>> Outgoing = rosterMembersService.GetAllFromAgency(agencyId);
            return Outgoing.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{agencyId}/members/details/id/{rosterMemberId}")
    public ResponseEntity<RosterMember> GetRosterMemberById(@PathVariable Long agencyId, @PathVariable Long rosterMemberId) {


        try {
            // Ensure the agency exists
            Optional<Agency> a = agencyService.findById(agencyId);
            if (a.isEmpty())
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

            Optional<RosterMember> Outgoing = rosterMembersService.GetOneByIdAndAgencyId(agencyId, rosterMemberId);
            return Outgoing.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{agencyId}/members/details/discord/{discordName}")
    public ResponseEntity<RosterMember> GetRosterMemberByDiscordName(@PathVariable Long agencyId, @PathVariable String discordName) {


        try {
            // Ensure the agency exists
            Optional<Agency> a = agencyService.findById(agencyId);
            if (a.isEmpty())
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

            Optional<RosterMember> Outgoing = rosterMembersService.GetOneByUnitCallSignAndAgencyId(discordName, agencyId, false);
            return Outgoing.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{agencyId}/members/details/callsign/{unitCallSign}")
    public ResponseEntity<RosterMember> GetRosterMemberByUnitCallSign(@PathVariable Long agencyId, @PathVariable String unitCallSign) {

        try {
            // Ensure the agency exists
            Optional<Agency> a = agencyService.findById(agencyId);
            if (a.isEmpty())
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

            Optional<RosterMember> Outgoing = rosterMembersService.GetOneByUnitCallSignAndAgencyId(unitCallSign, agencyId, true);
            return Outgoing.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
