package com.ismael.projects.projectmanagement.repositories;

import com.ismael.projects.projectmanagement.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
    Optional<Users> findByRegister(Long id);
}
