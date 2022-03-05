package com.myproject.bankv2.service;

import com.myproject.bankv2.dto.CardDTO;
import com.myproject.bankv2.exceptions.AccountNotFoundException;
import com.myproject.bankv2.exceptions.UserNotFoundException;
import com.myproject.bankv2.model.Account;
import com.myproject.bankv2.model.Card;
import com.myproject.bankv2.model.User;
import com.myproject.bankv2.repository.AccountRepository;
import com.myproject.bankv2.repository.CardRepository;
import com.myproject.bankv2.repository.UserRepository;
import com.myproject.bankv2.util.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CardServiceImpl {

    private final CardRepository cardRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository,
                           AccountRepository accountRepository,
                           UserRepository userRepository) {
        this.cardRepository = cardRepository;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public List<CardDTO> getCardsByAccount(String accountNumber) {
        List<CardDTO> result = new ArrayList<>();
        Account account = accountRepository.findByAccountNumber(accountNumber);
        List<Card> cards = cardRepository.getByAccount(account);
        cards.forEach(card -> result.add(new CardDTO(card)));
        return result;
    }


    public void saveCard(Card card) {
        User user = userRepository.findById(card.getUser().getId());
        if (user == null) {
            throw new UserNotFoundException();
        }
        Account account = accountRepository.findById(card.getAccount().getId());
        if (account == null) {
            throw new AccountNotFoundException("Account not found");
        }
        Card foundCard = cardRepository.findByCardNumber(card.getCardNumber());
        if (foundCard == null) {
            card.setCvv(Utilities.randomNumberCCV(1, 999));
            card.setActivationDate(LocalDate.now());
            card.setExpirationDate(card.getActivationDate().plusYears(4));
            card.setCardNumber(Utilities.generateCardNumber());
            card.setPin(Utilities.randomNumber(1000, 9999));
            card.setAmount(0);
            cardRepository.save(card);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void closeCard(String cardNumber) {
        cardRepository.delete(
                cardRepository.findByCardNumber(cardNumber));
    }

    public void changePin(Card card ) {
        cardRepository.save(card);
    }


    public List<CardDTO> getCardsByUser(User user){
        List<Card> cards = cardRepository.getByUser(user);
        List<CardDTO> result = new ArrayList<>();
        cards.forEach(card -> result.add(new CardDTO(card)));
        return result;
    }

    public Card getCardByCardNumber(String cardNumber){
        return cardRepository.findByCardNumber(cardNumber);
    }

    public void addMoney(Card card){
        cardRepository.save(card);
    }
}
