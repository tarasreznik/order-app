package com.example.orderapp.security;

import com.example.orderapp.models.User;
import com.example.orderapp.models.UserRole;
import com.example.orderapp.repos.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepo;

    public CustomUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User exsistingUser = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email: " + email + " not found"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(exsistingUser.getEmail())
                .password(exsistingUser.getPassword())
                .roles(String.valueOf(UserRole.CLIENT))
                .build();
    }
}
