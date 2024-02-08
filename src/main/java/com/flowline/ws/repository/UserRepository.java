package com.flowline.ws.repository;

import com.flowline.ws.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    boolean existsByEmail(String email);
    User findByActivationToken(String token);

    List<User> findAll();
}
