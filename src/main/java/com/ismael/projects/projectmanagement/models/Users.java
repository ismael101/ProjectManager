package com.ismael.projects.projectmanagement.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    @OneToOne
    private Register register;
    private Boolean accountLocked;
    private Boolean credentialsExpired;
    private Boolean accountExpired;
    private Boolean accountEnabled;
    @ManyToOne
    private Roles role;
    @ManyToOne
    private Teams team;

    public Users() {
    }

    public Users(Long id, String username, String password, Register register, Boolean accountLocked, Boolean credentialsExpired, Boolean accountExpired, Boolean accountEnabled, Roles role, Teams team) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.register = register;
        this.accountLocked = accountLocked;
        this.credentialsExpired = credentialsExpired;
        this.accountExpired = accountExpired;
        this.accountEnabled = accountEnabled;
        this.role = role;
        this.team = team;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
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

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public Teams getTeam() {
        return team;
    }

    public void setTeam(Teams team) {
        this.team = team;
    }

    public List<String> getPermissions(){
        List<String> permissions = null;
        if (this.getRole().getName() == "ADMIN") {
            permissions = List.of("REGISTER_READ", "REGISTER_WRITE", "PROJECT_READ", "PROJECT_WRITE", "TASK_READ", "TASK_WRITE");
        } else if (this.getRole().getName() == "LEAD") {
            permissions = List.of("PROJECT_READ", "PROJECT_WRITE", "TASK_READ", "TASK_WRITE");
        } else if (this.getRole().getName() == "MEMBER") {
            permissions = List.of("TASK_READ", "TASK_WRITE");
        }
        return permissions;
    }
}
