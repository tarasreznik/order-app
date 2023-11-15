package com.example.orderapp.services;

import com.example.orderapp.exceptions.ItemAlreadyExistsException;
import com.example.orderapp.exceptions.ResourceNotFoundException;
import com.example.orderapp.models.User;
import com.example.orderapp.models.UserModel;
import com.example.orderapp.models.UserRole;
import com.example.orderapp.repos.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;

    private final PasswordEncoder bcryptEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepo, PasswordEncoder bcryptEncoder) {
        this.userRepo = userRepo;
        this.bcryptEncoder = bcryptEncoder;
    }

    @Override
    public User createUser(UserModel user) {
        if (Boolean.TRUE.equals(userRepo.existsByEmail(user.getEmail()))) {
            throw new ItemAlreadyExistsException("User with email " + user.getEmail() + " is already exists");
        }
        User newUser = new User();
        BeanUtils.copyProperties(user, newUser);
        newUser.setRole(UserRole.CLIENT);
        newUser.setPassword(bcryptEncoder.encode(newUser.getPassword()));
        return userRepo.save(newUser);
    }

    @Override
    public User readUser() {
        Long userId = getLoggedInUser().getId();
        return userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for id:" + userId));
    }


    @Override
    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        return userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User with email: " + email + " not found"));
    }
}