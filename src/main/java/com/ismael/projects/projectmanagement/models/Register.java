package com.ismael.projects.projectmanagement.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Register {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private Boolean accountLocked;
    private Boolean credentialsExpired;
    private Boolean accountExpired;
    private Boolean accountEnabled;
    @ManyToOne
    private Teams team;
    @ManyToOne
    private Roles role;

    public Register() {
    }

    public Register(String email, Boolean accountLocked, Boolean credentialsExpired, Boolean accountExpired, Boolean accountEnabled, Teams team, Roles role) {
        this.email = email;
        this.accountLocked = accountLocked;
        this.credentialsExpired = credentialsExpired;
        this.accountExpired = accountExpired;
        this.accountEnabled = accountEnabled;
        this.team = team;
        this.role = role;
    }

    public Register(String email, Boolean accountLocked, Boolean credentialsExpired, Boolean accountExpired, Boolean accountEnabled, Roles role) {
        this.email = email;
        this.accountLocked = accountLocked;
        this.credentialsExpired = credentialsExpired;
        this.accountExpired = accountExpired;
        this.accountEnabled = accountEnabled;
        this.role = role;
    }

    public Register(Long id, String email, Boolean accountLocked, Boolean credentialsExpired, Boolean accountExpired, Boolean accountEnabled, Teams team, Roles role) {
        this.id = id;
        this.email = email;
        this.accountLocked = accountLocked;
        this.credentialsExpired = credentialsExpired;
        this.accountExpired = accountExpired;
        this.accountEnabled = accountEnabled;
        this.team = team;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(Boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public Boolean getCredentialsExpired() {
        return credentialsExpired;
    }

    public void setCredentialsExpired(Boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    public Boolean getAccountExpired() {
        return accountExpired;
    }

    public void setAccountExpired(Boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    public Boolean getAccountEnabled() {
        return accountEnabled;
    }

    public void setAccountEnabled(Boolean accountEnabled) {
        this.accountEnabled = accountEnabled;
    }

    public Teams getTeam() {
        return team;
    }

    public void setTeam(Teams team) {
        this.team = team;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

}
