package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor @NoArgsConstructor @Builder
@Entity
@Table(name = "product_user", schema = "public")
public class ProductUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private Double price;

    @Getter @Setter
    private int stock;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @Getter @Setter
    private User user;

    @ManyToOne
    @JoinColumn(name = "products_id",referencedColumnName = "id")
    @Getter @Setter
    private Product product;

    public ProductUser(Double price, int stock, User user, Product product) {
        this.price = price;
        this.stock = stock;
        this.user = user;
        this.product = product;
    }
}
