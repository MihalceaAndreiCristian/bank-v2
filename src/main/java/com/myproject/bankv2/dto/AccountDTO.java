package com.myproject.bankv2.dto;

import com.myproject.bankv2.model.Account;
import com.myproject.bankv2.model.Card;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class AccountDTO {

    private String currency;
    private String accountNumber;
    private double amount;
//    private List<String> cardsNumber;
    private String userFirstName;

    public AccountDTO(Account account) {
        this.currency = account.getCurrency();
        this.accountNumber = account.getAccountNumber();
        this.amount = account.getAmount();
//        this.cardsNumber =
//                account
//                        .getCards()
//                        .stream()
//                        .map(Card::getCardNumber)
//                        .collect(Collectors.toList());
        this.userFirstName = account.getUser().getFirstName();
    }
}
