package com.myproject.bankv2.repository;

import com.myproject.bankv2.model.Account;
import com.myproject.bankv2.model.Card;
import com.myproject.bankv2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card,Integer> {

    List<Card> getByAccount(Account account);
    Card findByCardNumber(String cardNumber);
    List<Card> getByUser(User user);

}
