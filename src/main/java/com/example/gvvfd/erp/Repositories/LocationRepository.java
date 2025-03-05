package com.example.gvvfd.erp.Repositories;

import com.example.gvvfd.erp.Models.Agency;
import com.example.gvvfd.erp.Models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository  extends JpaRepository<Location, Long> {
    // You can define custom queries here if needed
    Optional<List<Location>> findByAgencyId(Long AgencyId);
}
