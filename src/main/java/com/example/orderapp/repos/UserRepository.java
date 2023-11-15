package com.example.orderapp.repos;

import com.example.orderapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email);

    Optional<User> findById(Long userId);

    Optional<User> findByEmail(String email);
}
