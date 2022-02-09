package com.ismael.projects.projectmanagement.repositories;

import com.ismael.projects.projectmanagement.models.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Projects, Long> {
    Projects findByName(String name);
}
