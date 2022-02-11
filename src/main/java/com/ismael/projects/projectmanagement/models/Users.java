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
    Register register;

    public Users() {
    }

    public Users(String username, String password, Register register) {
        this.username = username;
        this.password = password;
        this.register = register;
    }

    public Users(Long id, String username, String password, Register register) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.register = register;
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

    /*
    public List<String> getPermissions(){
        List<String> permissions = null;
        if (this.register.getRole().getName() == "ADMIN") {
            permissions = List.of("REGISTER_READ", "REGISTER_WRITE", "PROJECT_READ", "PROJECT_WRITE", "TASK_READ", "TASK_WRITE");
        } else if (this.register.getRole().getName() == "LEAD") {
            permissions = List.of("PROJECT_READ", "PROJECT_WRITE", "TASK_READ", "TASK_WRITE");
        } else if (this.register.getRole().getName() == "MEMBER") {
            permissions = List.of("TASK_READ", "TASK_WRITE");
        }
        return permissions;
    }

     */
}
