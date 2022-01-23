package com.example.demo.dao;

import com.example.demo.model.User;
import com.example.demo.util.RolesEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findAllByRole(String role);
}
