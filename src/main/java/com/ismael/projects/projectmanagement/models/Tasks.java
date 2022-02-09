package com.ismael.projects.projectmanagement.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Boolean complete;
    @ManyToOne
    private Projects project;
    private LocalDateTime createdAt;
    private Date updatedAt;

    public Tasks() {
    }

    public Tasks(Long id, String name, Boolean complete, Projects project, LocalDateTime createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.complete = complete;
        this.project = project;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public Projects getProject() {
        return project;
    }

    public void setProject(Projects project) {
        this.project = project;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
