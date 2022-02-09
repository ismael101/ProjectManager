package com.ismael.projects.projectmanagement.repositories;

import com.ismael.projects.projectmanagement.models.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Long> {
    Register findRegisterByEmail(String email);
}
