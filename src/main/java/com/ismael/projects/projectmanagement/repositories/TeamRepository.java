package com.ismael.projects.projectmanagement.repositories;

import com.ismael.projects.projectmanagement.models.Teams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Teams, Long> {
    Teams findByName(String name);

}
