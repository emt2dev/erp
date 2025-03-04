package com.example.gvvfd.erp.Repositories;

import com.example.gvvfd.erp.Models.RosterMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RosterMembersRepository extends JpaRepository<RosterMember, Long> {
    // You can define custom queries here if needed
}
