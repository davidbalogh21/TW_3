package com.example.demo.service.user;

import com.example.demo.model.User;
import com.example.demo.util.RolesEnum;

import java.util.List;


public interface UserService {
    void save(User user);
    User findByUsername(String username);
    List<User> findByRole(String role);
}