package com.example.demo.service.product;

import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.util.RolesEnum;

import java.util.List;

public interface ProductService {
    List<Product> listProduct();
    List<Product> getProductsByUserRole(List<User> users);
}
