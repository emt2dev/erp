package com.example.gvvfd.erp.Repositories;


import com.example.gvvfd.erp.Models.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {
    // You can add custom queries if necessary
}