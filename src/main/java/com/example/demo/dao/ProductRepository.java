package com.example.demo.dao;

import com.example.demo.model.Product;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> getAllByUser(User user);
    List<Product> findByUserIn(List<User> users);
}
