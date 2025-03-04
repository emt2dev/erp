package com.example.gvvfd.erp.Repositories;

import com.example.gvvfd.erp.Models.User;
import com.example.gvvfd.erp.Models.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstByUsername(String UserName);
    Optional<User> findByUserRole(UserRole role);

}
