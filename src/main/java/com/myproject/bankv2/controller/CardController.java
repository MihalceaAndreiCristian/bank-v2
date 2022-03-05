package com.myproject.bankv2.controller;

import com.myproject.bankv2.dto.CardDTO;
import com.myproject.bankv2.model.Account;
import com.myproject.bankv2.model.Card;
import com.myproject.bankv2.model.User;
import com.myproject.bankv2.service.AccountServiceImpl;
import com.myproject.bankv2.service.CardServiceImpl;
import com.myproject.bankv2.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/card")
public class CardController {


    private final CardServiceImpl cardService;
    private final AccountServiceImpl accountService;
    private final UserServiceImpl userService;

    @Autowired
    public CardController(CardServiceImpl cardService, AccountServiceImpl accountService, UserServiceImpl userService) {
        this.cardService = cardService;
        this.accountService = accountService;
        this.userService = userService;
    }


    @GetMapping("/cards")
    public String getCardByAccountNumber(@RequestParam("accountNumber") String accountNumber, Model model) {
        List<CardDTO> cards = cardService.getCardsByAccount(accountNumber);
        Account account = accountService.getAccountByAccountNumber(accountNumber);
        model.addAttribute("account", account);
        if (!cards.isEmpty()) {
            model.addAttribute("cards", cards);
        }
        return "card-details";
    }

    @GetMapping("/cardlist")
    public String getCardsByUser(@RequestParam("username") String username, Model model) {
        User user = userService.getUser(username);
        List<CardDTO> cards = cardService.getCardsByUser(user);
        model.addAttribute("user", user);
        if (!cards.isEmpty()) {
            model.addAttribute("cards", cards);
        }
        return "card-list";
    }

    @GetMapping("/create")
    public String createCard(@RequestParam("accountNumber") String accountNumber,
                             @ModelAttribute("card") Card card, Model model) {
        Account account = accountService.getAccountByAccountNumber(accountNumber);
        card.setAccount(account);
        card.setUser(account.getUser());
        cardService.saveCard(card);
        List<CardDTO> cards = cardService.getCardsByAccount(accountNumber);
        model.addAttribute("account", account);
        if (!cards.isEmpty()) {
            model.addAttribute("cards", cards);
        }
        return "card-details";
    }

    @GetMapping("/delete")
    public String deleteCard(@RequestParam("cardNumber") String cardNumber) {
        cardService.closeCard(cardNumber);
        return "action-done";
    }

    @GetMapping("/card-details")
    public String getDetailsOfCard(@RequestParam("cardNumber") String cardNumber, Model model) {
        Card card = cardService.getCardByCardNumber(cardNumber);
        Account account = accountService.getAccountByAccountNumber(card.getAccount().getAccountNumber());
        model.addAttribute("account", account);
        model.addAttribute("card", card);
        return "card-actions";
    }


    @PostMapping("/change-pin")
    public String popUpPinChange(@RequestParam("cardNumber") String cardNumber, @ModelAttribute("card") Card card, Model model) {
        Card card1 = cardService.getCardByCardNumber(cardNumber);
        Account account = accountService.getAccountByAccountNumber(card1.getAccount().getAccountNumber());
        model.addAttribute("account", account);
        model.addAttribute("card", card1);
        card1.setPin(card.getPin());
        cardService.changePin(card1);
        return "card-actions";
    }


    @GetMapping("/transaction")
    public String transactions(@RequestParam("cardNumber") String cardNumber, Model model) {
        Card card = cardService.getCardByCardNumber(cardNumber);
        model.addAttribute("card", card);
        return "transaction";
    }

    @PostMapping("/add-money")
    public String addMoney(@RequestParam("cardNumber") String cardNumber, @ModelAttribute("card1") Card card1, Model model) {
        Card card = cardService.getCardByCardNumber(cardNumber);
        Account account = accountService.getAccountByAccountNumber(card.getAccount().getAccountNumber());
        model.addAttribute("account", account);
        model.addAttribute("card", card);
        model.addAttribute("card1", card1);
        card.setAmount(card.getAmount() + card1.getAmount());
        cardService.addMoney(card);
        return "card-actions";
    }

    @PostMapping("/withdraw-money")
    public String withDrawMoney(@RequestParam("cardNumber") String cardNumber, @ModelAttribute("card1") Card card1, Model model) {
        Card card = cardService.getCardByCardNumber(cardNumber);
        Account account = accountService.getAccountByAccountNumber(card.getAccount().getAccountNumber());
        model.addAttribute("account", account);
        model.addAttribute("card", card);
        model.addAttribute("card1", card1);
        if (card1.getAmount() < card.getAmount()) {
            card.setAmount((card.getAmount()) - (card1.getAmount()));
        }
        cardService.addMoney(card);
        return "card-actions";
    }

}
