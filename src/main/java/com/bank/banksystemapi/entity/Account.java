package com.bank.banksystemapi.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.*;

import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(unique = true, nullable = false)
    @Size(min = 16, max = 16, message = "card number must be 16 characters long")
    private String cardNumber;

    @Column(nullable = false)
    private String CVV;

    @Column(nullable = false)
    private Double balance;

    @Column(nullable = false)
    private Boolean status;

    @Column(nullable = false)
    private Timestamp createdAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "user_id")
    private User user;


    public Account(String cardNumber, String CVV, Double balance, Boolean status, Timestamp createdAt, User user) {
        this.cardNumber = cardNumber;
        this.CVV = CVV;
        this.balance = balance;
        this.status = status;
        this.createdAt = createdAt;
        this.user = user;
    }


}
