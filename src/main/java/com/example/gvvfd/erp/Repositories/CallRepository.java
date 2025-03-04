package com.example.gvvfd.erp.Repositories;

import com.example.gvvfd.erp.Models.Call;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CallRepository extends JpaRepository<Call, Long> {

    List<Call> findByAgencyIdAndActiveTrue(Long agencyId);
}
