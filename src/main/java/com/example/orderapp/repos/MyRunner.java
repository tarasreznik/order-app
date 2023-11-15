package com.example.orderapp.repos;

import com.example.orderapp.models.User;
import com.example.orderapp.models.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * This runner is created for insert a manager to the db
 */
@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    UserRepository userRepo;

    @Override
    public void run(String... args) throws Exception {
        if (userRepo.findAll().isEmpty()) {
            userRepo.save(new User("manager@gmail.com", "Manager", "12345", UserRole.MANAGER));
        }
    }
}
