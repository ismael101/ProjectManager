package com.ismael.projects.projectmanagement.initialize;

import com.ismael.projects.projectmanagement.models.Register;
import com.ismael.projects.projectmanagement.models.Roles;
import com.ismael.projects.projectmanagement.models.Users;
import com.ismael.projects.projectmanagement.repositories.RegisterRepository;
import com.ismael.projects.projectmanagement.repositories.RolesRepository;
import com.ismael.projects.projectmanagement.repositories.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

public class Initialize implements CommandLineRunner {
    private UsersRepository usersRepository;
    private RegisterRepository registerRepository;
    private RolesRepository rolesRepository;
    private PasswordEncoder passwordEncoder;
    private String username;
    private String password;
    private String email;

    public Initialize(UsersRepository usersRepository, RegisterRepository registerRepository, RolesRepository rolesRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.registerRepository = registerRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
        username = System.getenv("USERNAME");
        password = System.getenv("PASSWORD");
        email = System.getenv("EMAIL");
    }

    public void createRoles(){
        Roles admin = new Roles("ADMIN");
        Roles lead = new Roles("LEAD");
        Roles member = new Roles("MEMBER");
        this.rolesRepository.saveAll(Arrays.asList(admin, lead, member));
    }

    public void registerAdmin(){
        Register register = new Register(email, true, false, false, true, this.rolesRepository.findByName("ADMIN"));
        this.registerRepository.save(register);
    }

    public void createAdmin(){
        Register register = this.registerRepository.findRegisterByEmail(email).get();
        Users admin = new Users(username, this.passwordEncoder.encode(password), register);
        this.usersRepository.save(admin);
    }

    @Override
    public void run(String... args) throws Exception {
        this.createRoles();
        this.registerAdmin();
        this.createAdmin();
    }
}
