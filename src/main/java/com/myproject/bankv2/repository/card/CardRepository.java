package com.myproject.bankv2.repository.card;

import com.myproject.bankv2.model.Account;
import com.myproject.bankv2.model.Card;
import com.myproject.bankv2.model.User;

import java.util.List;

public interface CardRepository {

    List<Card> getAllCards();
    void saveCard(Card card);
    void deleteCard(int id);
    List<Card> getCardsByAccount(Account account);
    Card getCardById(int id);
    Card getCardByCardNumber(String cardNumber);
    List<Card> getCardsByUser(User user);
}
