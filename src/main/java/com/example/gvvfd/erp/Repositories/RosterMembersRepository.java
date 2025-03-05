package com.example.gvvfd.erp.Repositories;

import com.example.gvvfd.erp.Models.RosterMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RosterMembersRepository extends JpaRepository<RosterMember, Long> {
    Optional<List<RosterMember>> findByAgencyId(Long agencyId);
    Optional<RosterMember> findByIdAndAgencyId(Long agencyId, Long Id);
    Optional<RosterMember> findByUnitCallSignAndAgencyId(String unitCallSign, Long agencyId);
    Optional<RosterMember> findByDiscordNameAndAgencyId(String discordName, Long agencyId);
}
