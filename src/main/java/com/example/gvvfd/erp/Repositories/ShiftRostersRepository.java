package com.example.gvvfd.erp.Repositories;

import com.example.gvvfd.erp.Models.ShiftRoster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftRostersRepository extends JpaRepository<ShiftRoster, Long> {

}