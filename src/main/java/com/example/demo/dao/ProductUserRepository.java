package com.example.demo.dao;

import com.example.demo.model.Product;
import com.example.demo.model.ProductUser;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductUserRepository extends JpaRepository<ProductUser, Long> {
    List<ProductUser> getAllByUserOrderByPriceAsc(User user);
    List<ProductUser> findByUserInOrderByPriceAsc(List<User> users);
}

