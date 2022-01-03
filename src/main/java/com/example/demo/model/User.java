package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor @NoArgsConstructor @Builder
@Entity
@Table(name = "user", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String username;
    @Getter @Setter
    private String password;

    @Transient
    @Getter @Setter
    private String passwordConfirm;

    @ManyToMany
    @Getter @Setter
    private Set<Role> roles;

    @OneToMany
    @Getter @Setter
    private List<Receipt> receipts = new ArrayList<>();
}