package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "receipt", schema = "public")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private Double amount;

    @Getter @Setter
    private int quantity;

    @Getter @Setter
    private LocalDate date;

    @Getter @Setter
    private String productName;

    @Getter @Setter
    private Double unitPrice;

    @Getter @Setter
    private String dealer;

    @ManyToOne
    @Getter @Setter
    private User user;

    public Receipt(Double amount, int quantity, LocalDate date, String productName, Double unitPrice, String dealer, User user) {
        this.amount = amount;
        this.quantity = quantity;
        this.date = date;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.dealer = dealer;
        this.user = user;
    }
}
