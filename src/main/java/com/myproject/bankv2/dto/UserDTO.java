package com.myproject.bankv2.dto;

import com.myproject.bankv2.model.Account;
import com.myproject.bankv2.model.Card;
import com.myproject.bankv2.model.User;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDTO {
    private String username;
    private String fullName;

    private String email;
    private Date birthDate;
    private List<String> bankAccountsNumber;
    private List<String> cardsNumber;


    public UserDTO(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.bankAccountsNumber =
                user
                        .getAccounts()
                        .stream()
                        .map(Account::getAccountNumber)
                        .collect(Collectors.toList());
        this.cardsNumber =
                user
                        .getCards()
                        .stream()
                        .map(Card::getCardNumber)
                        .collect(Collectors.toList());
        this.birthDate=user.getBirthDate();
        this.fullName = user.getFullName();
    }
}
