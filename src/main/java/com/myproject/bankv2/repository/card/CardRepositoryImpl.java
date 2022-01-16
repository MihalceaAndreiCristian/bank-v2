package com.myproject.bankv2.repository.card;

import com.myproject.bankv2.model.Account;
import com.myproject.bankv2.model.Card;
import com.myproject.bankv2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CardRepositoryImpl implements CardRepository{

    private final CardRepo cardRepo;

    @Autowired
    public CardRepositoryImpl(CardRepo cardRepo) {
        this.cardRepo = cardRepo;
    }

    @Override
    public List<Card> getAllCards() {
        return cardRepo.findAll();
    }

    @Override
    public void saveCard(Card card) {
        cardRepo.save(card);
    }

    @Override
    public void deleteCard(int id) {
        cardRepo.deleteById(id);
    }

    @Override
    public List<Card> getCardsByAccount(Account account) {
        return cardRepo.getByAccount(account);
    }

    @Override
    public Card getCardById(int id) {
        return cardRepo.findById(id);
    }

    @Override
    public Card getCardByCardNumber(String cardNumber) {
        return cardRepo.findByCardNumber(cardNumber);
    }

    @Override
    public List<Card> getCardsByUser(User user) {
        return cardRepo.getByUser(user);
    }
}
