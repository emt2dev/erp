package com.example.gvvfd.erp.Repositories;

import com.example.gvvfd.erp.Models.IncidentReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentReportsRepository extends JpaRepository<IncidentReport, Long> {
}
