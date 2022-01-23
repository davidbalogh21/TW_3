package com.example.demo.service.product;

import com.example.demo.dao.ProductRepository;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> listProduct() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByUser(User user) {return productRepository.getAllByUser(user);}

    public List<Product> getProductsByUserRole(List<User> users) {return productRepository.findByUserIn(users);}

}
