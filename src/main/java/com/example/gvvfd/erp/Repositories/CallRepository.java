package com.example.gvvfd.erp.Repositories;

import com.example.gvvfd.erp.Models.Call;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CallRepository extends JpaRepository<Call, Long> {
    Optional<Call> findByIdAndAgencyId(Long Id, Long agencyId);
    Optional<List<Call>> findByAgencyId(Long agencyId);
    Optional<List<Call>> findByAgencyIdAndActiveTrue(Long agencyId);
    Optional<List<Call>> findByAgencyIdAndShiftId(Long agencyId, Long shiftId);
}
