package com.example.gvvfd.erp.Repositories;

import com.example.gvvfd.erp.Models.UserActions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActionsRepository extends JpaRepository<UserActions, Long> {
    // You can add custom queries if necessary
}