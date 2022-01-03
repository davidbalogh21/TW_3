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
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String location;
    @Getter
    @Setter
    private Double amount;
    @Getter
    @Setter
    private LocalDate date;

    @ManyToOne
    @Getter @Setter
    private User user;
}
