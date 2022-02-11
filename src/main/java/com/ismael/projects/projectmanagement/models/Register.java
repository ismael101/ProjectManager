package com.ismael.projects.projectmanagement.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Register {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean accountNonExpired;
    private Boolean accountEnabled;
    @ManyToOne
    private Teams team;
    @ManyToOne
    private Roles role;

    public Register() {
    }

    public Register(String email, Boolean accountNonLocked, Boolean credentialsNonExpired, Boolean accountNonExpired, Boolean accountEnabled, Teams team, Roles role) {
        this.email = email;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonExpired = accountNonExpired;
        this.accountEnabled = accountEnabled;
        this.team = team;
        this.role = role;
    }

    public Register(Long id, String email, Boolean accountNonLocked, Boolean credentialsNonExpired, Boolean accountNonExpired, Boolean accountEnabled, Teams team, Roles role) {
        this.id = id;
        this.email = email;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonExpired = accountNonExpired;
        this.accountEnabled = accountEnabled;
        this.team = team;
        this.role = role;
    }


    public Register(String email, Boolean accountNonLocked, Boolean credentialsNonExpired, Boolean accountNonExpired, Boolean accountEnabled) {
        this.email = email;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonExpired = accountNonExpired;
        this.accountEnabled = accountEnabled;
    }

    public Register(String email, Boolean accountNonLocked, Boolean credentialsNonExpired, Boolean accountNonExpired, Boolean accountEnabled, Roles role) {
        this.email = email;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonExpired = accountNonExpired;
        this.accountEnabled = accountEnabled;
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

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
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
