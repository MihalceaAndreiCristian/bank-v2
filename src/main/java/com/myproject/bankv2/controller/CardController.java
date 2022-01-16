package com.myproject.bankv2.controller;

import com.myproject.bankv2.dto.CardDTO;
import com.myproject.bankv2.model.Account;
import com.myproject.bankv2.model.Card;
import com.myproject.bankv2.model.User;
import com.myproject.bankv2.service.AccountService;
import com.myproject.bankv2.service.CardService;
import com.myproject.bankv2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api/card")
public class CardController {


    private final CardService cardService;
    private final AccountService accountService;
    private final UserService userService;

    @Autowired
    public CardController(CardService cardService, AccountService accountService, UserService userService) {
        this.cardService = cardService;
        this.accountService = accountService;
        this.userService = userService;
    }


    @GetMapping("/cards")
    public String getCardByAccountNumber(@RequestParam("accountNumber") String accountNumber, Model model){
        List<CardDTO> cards = cardService.getCardsByAccount(accountNumber);
        Account account = accountService.getAccountByAccountNumber(accountNumber);
        model.addAttribute("account",account);
//        User user = userService.getUser(account.getUser().getUsername());
//        model.addAttribute("user",user);
        if (!cards.isEmpty()){
            model.addAttribute("cards",cards);
        }
        return "card-details";
    }

    @GetMapping("/cardlist")
    public String getCardsByUser(@RequestParam("username") String username, Model model){
        User user = userService.getUser(username);
        List<CardDTO> cards = cardService.getCardsByUser(user);
        model.addAttribute("user",user);
        if (!cards.isEmpty()) {
            model.addAttribute("cards", cards);
        }
        return "card-list";
    }

    @GetMapping("/create")
    public String createCard(@RequestParam("accountNumber") String accountNumber,
                             @ModelAttribute("card") Card card, Model model ){
        Account account = accountService.getAccountByAccountNumber(accountNumber);
        card.setAccount(account);
        card.setUser(account.getUser());
        cardService.saveCard(card);
        List<CardDTO> cards = cardService.getCardsByAccount(accountNumber);
        model.addAttribute("account",account);
        if (!cards.isEmpty()){
            model.addAttribute("cards",cards);
        }
        return "card-details";
    }

    @GetMapping("/delete")
    public String deleteCard(@RequestParam("cardNumber") String cardNumber){
        cardService.closeCard(cardNumber);
        return "action-done";
    }

}
