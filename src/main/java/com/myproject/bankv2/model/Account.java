package com.myproject.bankv2.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    private String currency;
    private String accountNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:ss")
    private LocalDateTime accountCreatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "account")
    private List<Card> cards;

    private double amount;



    public Account(User user, String currency) {
        this.currency = currency;
        this.user = user;
    }


}
