package com.example.demo.service.product_user;

import com.example.demo.dao.ProductRepository;
import com.example.demo.dao.ProductUserRepository;
import com.example.demo.model.Product;
import com.example.demo.model.ProductUser;
import com.example.demo.model.User;
import com.example.demo.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductUserServiceImpl implements ProductUserService {
    private final ProductUserRepository productUserRepository;

    @Autowired
    public ProductUserServiceImpl(ProductUserRepository productUserRepository) {
        this.productUserRepository = productUserRepository;
    }

    @Override
    public List<ProductUser> listProducts() {return productUserRepository.findAll();}

    public List<ProductUser> getProductsByUser(User user) {return productUserRepository.getAllByUserOrderByPriceAsc(user);}

    public List<ProductUser> getProductsByUserRole(List<User> users) {return productUserRepository.findByUserInOrderByPriceAsc(users);}

    public void save (ProductUser productUser) {productUserRepository.save(productUser);}

    public ProductUser getProductUserById (Long id) {return productUserRepository.getById(id);}
}


