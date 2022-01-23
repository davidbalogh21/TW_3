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

    @Getter @Setter
    private Double price;

    @Getter @Setter
    private Long stock;

    @ManyToMany(mappedBy = "productList",fetch = FetchType.EAGER)
    @Getter @Setter
    private List<User> user;
}
