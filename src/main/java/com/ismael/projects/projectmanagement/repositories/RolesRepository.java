package com.ismael.projects.projectmanagement.repositories;

import com.ismael.projects.projectmanagement.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
    Roles findByName(String name);

}
