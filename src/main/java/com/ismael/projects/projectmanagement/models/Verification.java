package com.ismael.projects.projectmanagement.models;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class Verification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true)
    private String token;
    @Column(nullable = false)
    private LocalDateTime expiredAt;
    @OneToOne
    @Column(nullable = false)
    private Users users;

    public Verification() {
    }

    public Verification(String token, LocalDateTime expiredAt) {
        this.token = token;
        this.expiredAt = expiredAt;
    }

    public Verification(Long id, String token, LocalDateTime expiredAt) {
        this.id = id;
        this.token = token;
        this.expiredAt = expiredAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(LocalDateTime expiredAt) {
        this.expiredAt = expiredAt;
    }
}
