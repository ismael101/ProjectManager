package com.ismael.projects.projectmanagement.repositories;

import com.ismael.projects.projectmanagement.models.Teams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Teams, Long> {
    Optional<Teams> findByName(String name);

}
