package com.ismael.projects.projectmanagement.security;

import com.ismael.projects.projectmanagement.models.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {
    private Users user;

    public MyUserDetails(Users user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority role = new SimpleGrantedAuthority("ROLE_" + this.user.getRegister().getRole().getName());
        authorities.add(role);
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getRegister().getAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
       return user.getRegister().getAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getRegister().getCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.getRegister().getAccountEnabled();
    }

    public Long getTeam(){
        return user.getRegister().getTeam().getId();
    }
}
