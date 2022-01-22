package com.myproject.bankv2.model;

import com.myproject.bankv2.util.Utilities;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Data
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    private String cardNumber;
    private String cvv;

    private int pin;

    private LocalDate activationDate;
    private LocalDate expirationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;


    public Card(User user, Account account) {
        this.cardNumber = Utilities.generateCardNumber();
        this.cvv = Utilities.randomNumberCCV(1, 999);
        this.activationDate = LocalDate.now();
        this.expirationDate = LocalDate.now().plusYears(4);
        this.user = user;
        this.account = account;

    }
}
