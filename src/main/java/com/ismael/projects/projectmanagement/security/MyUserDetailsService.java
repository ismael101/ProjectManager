package com.ismael.projects.projectmanagement.security;

import com.ismael.projects.projectmanagement.models.Users;
import com.ismael.projects.projectmanagement.repositories.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private UsersRepository userRepository;

    public MyUserDetailsService(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = this.userRepository.findByUsername(username).get();
        MyUserDetails userDetails = new MyUserDetails(user);
        return userDetails;
    }

}
