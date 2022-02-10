package com.ismael.projects.projectmanagement.repositories;

import com.ismael.projects.projectmanagement.models.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Long> {
    Optional<Register> findRegisterById(Long id);
    Optional<Register> findRegisterByEmail(String email);
}
