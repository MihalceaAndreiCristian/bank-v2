package com.myproject.bankv2.service;

import com.myproject.bankv2.dto.CardDTO;
import com.myproject.bankv2.exceptions.AccountNotFoundException;
import com.myproject.bankv2.exceptions.CardNotFoundException;
import com.myproject.bankv2.exceptions.UserNotFoundException;
import com.myproject.bankv2.model.Account;
import com.myproject.bankv2.model.Card;
import com.myproject.bankv2.model.User;
import com.myproject.bankv2.repository.account.AccountRepository;
import com.myproject.bankv2.repository.card.CardRepository;
import com.myproject.bankv2.repository.user.UserRepository;
import com.myproject.bankv2.util.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Autowired
    public CardService(CardRepository cardRepository, AccountRepository accountRepository, UserRepository userRepository) {
        this.cardRepository = cardRepository;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public List<CardDTO> getAllCards() {
        List<CardDTO> result = new ArrayList<>();
        List<Card> cards = cardRepository.getAllCards();
        cards.stream().forEach(card -> result.add(new CardDTO(card)));
        if (!result.isEmpty()) {
            return result;
        }
        throw new CardNotFoundException("No cards in data base");
    }

    public List<CardDTO> getCardsByAccount(String accountNumber) {
        List<CardDTO> result = new ArrayList<>();
        Account account = accountRepository.getAccountByAccountNumber(accountNumber);
        List<Card> cards = cardRepository.getCardsByAccount(account);
        cards.forEach(card -> result.add(new CardDTO(card)));
        return result;
    }


    public void saveCard(Card card) {
        User user = userRepository.findById(card.getUser().getId());
        if (user == null) {
            throw new UserNotFoundException();
        }
        Account account = accountRepository.getAccountById(card.getAccount().getId());
        if (account == null) {
            throw new AccountNotFoundException("Account not found");
        }
        Card foundCard = cardRepository.getCardByCardNumber(card.getCardNumber());
        if (foundCard == null) {
            card.setCvv(Utilities.randomNumberCCV(1, 999));
            card.setActivationDate(LocalDate.now());
            card.setExpirationDate(card.getActivationDate().plusYears(4));
            card.setCardNumber(Utilities.generateCardNumber());
            card.setPin(Utilities.randomNumber(1000, 9999));
            cardRepository.saveCard(card);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void closeCard(String cardNumber) {
        cardRepository.deleteCard(
                cardRepository.getCardByCardNumber(cardNumber).getId()
        );
    }

    public void changePin(Card card ) {
        cardRepository.saveCard(card);
    }


    public List<CardDTO> getCardsByUser(User user){
        List<Card> cards = cardRepository.getCardsByUser(user);
        List<CardDTO> result = new ArrayList<>();
        cards.forEach(card -> result.add(new CardDTO(card)));
        return result;
    }

    public Card getCardByCardNumber(String cardNumber){
        return cardRepository.getCardByCardNumber(cardNumber);

    }
}
