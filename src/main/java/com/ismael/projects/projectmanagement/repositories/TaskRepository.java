package com.ismael.projects.projectmanagement.repositories;

import com.ismael.projects.projectmanagement.models.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Tasks, Long> {
    Optional<Tasks> findByName(String name);

}
