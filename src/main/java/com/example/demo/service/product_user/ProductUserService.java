package com.example.demo.service.product_user;

import com.example.demo.model.Product;
import com.example.demo.model.ProductUser;
import com.example.demo.model.User;

import java.util.List;

public interface ProductUserService {
    List<ProductUser> listProducts();

    List<ProductUser> getProductsByUser(User user);

     List<ProductUser> getProductsByUserRole(List<User> users);
}
