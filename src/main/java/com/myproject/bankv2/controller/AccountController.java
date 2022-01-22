package com.myproject.bankv2.controller;

import com.myproject.bankv2.dto.AccountDTO;
import com.myproject.bankv2.model.Account;
import com.myproject.bankv2.model.User;
import com.myproject.bankv2.service.AccountService;
import com.myproject.bankv2.service.UserService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;
    private final UserService userService;

    @Autowired
    public AccountController(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @GetMapping("/accounts")
    public String getAccountsByUser(@RequestParam("username") String username, Model model){
        List<AccountDTO> accounts = accountService.getByUser(username);
        User user = userService.getUser(username);
        model.addAttribute("user",user);
        if (!accounts.isEmpty()){
            model.addAttribute("accounts",accounts);
        }
        return "accounts-details";
    }



    @PostMapping("/create")
    public String saveAccount(@RequestParam("username") String username,
                              @ModelAttribute("account")Account account,
                              Model model){
        User user = userService.getUser(username);
        account.setUser(user);
        accountService.createAccount(account);

        List<AccountDTO > accounts = accountService.getByUser(username);
        model.addAttribute("user", user);
        if (!accounts.isEmpty()) {
            model.addAttribute("accounts", accounts);
        }
        return "accounts-details";
    }

    @GetMapping("/new")
    public String newAccount(@RequestParam("userId") int userId,Model model){
        Account account = new Account();
        User user = userService.getUserById(userId);
        account.setUser(user);
        model.addAttribute("user", user);
        model.addAttribute("account", account);
        return "add-account-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("accountNumber") String accountNumber){
        accountService.deleteAccount(accountNumber);
        return "action-done";
    }
}
