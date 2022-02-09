package com.ismael.projects.projectmanagement.repositories;

import com.ismael.projects.projectmanagement.models.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Tasks, Long> {
    Tasks findByName(String name);

}
