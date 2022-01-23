package com.myproject.bankv2.dto;

import com.myproject.bankv2.model.Card;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CardDTO {

    private String cardNumber;
    private String cvv;
    private String accountNumber;
    private String userFirstName;
    private LocalDate activationDate;
    private LocalDate expirationDate;
    private double amount;

    public CardDTO(Card card) {
        this.cardNumber = card.getCardNumber();
        this.cvv = card.getCvv();
        this.accountNumber = card.getAccount().getAccountNumber();
        this.userFirstName = card.getUser().getFirstName();
        this.activationDate = card.getActivationDate();
        this.expirationDate = card.getExpirationDate();
        this.amount = card.getAmount();
    }
}
