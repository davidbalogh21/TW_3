package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor @NoArgsConstructor @Builder
@Entity
@Table(name = "product", schema = "public")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String name;

    @ManyToMany(mappedBy = "productList",fetch = FetchType.EAGER)
    @Getter @Setter
    private List<User> user;

    public Product(String name, List<User> user) {
        this.name = name;
        this.user = user;
    }
}
