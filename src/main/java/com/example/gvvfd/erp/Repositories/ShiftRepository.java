package com.example.gvvfd.erp.Repositories;


import com.example.gvvfd.erp.Models.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {
    Optional<Shift> findByIdAndAgencyId(Long id, Long agencyId);
}