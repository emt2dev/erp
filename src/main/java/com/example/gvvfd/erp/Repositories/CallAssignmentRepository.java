package com.example.gvvfd.erp.Repositories;

import com.example.gvvfd.erp.Models.CallAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallAssignmentRepository extends JpaRepository<CallAssignment, Long> {
    // You can define custom queries here if needed
}
