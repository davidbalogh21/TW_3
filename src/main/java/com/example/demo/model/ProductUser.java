package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor @NoArgsConstructor @Builder
@Entity
@Table(name = "user_products", schema = "public")
public class ProductUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private Double price;

    @Getter @Setter
    private int stock;

    @ManyToMany(mappedBy = "productList",fetch = FetchType.EAGER)
    @Getter @Setter
    private List<User> user;

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(name = "user_products", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "products_id"))
    private List<Product> productList = new ArrayList<>();
}
