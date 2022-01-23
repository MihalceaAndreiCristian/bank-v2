package com.myproject.bankv2.service;

import com.myproject.bankv2.dto.AccountDTO;
import com.myproject.bankv2.exceptions.AccountNotFoundException;
import com.myproject.bankv2.exceptions.UserNotFoundException;
import com.myproject.bankv2.model.Account;
import com.myproject.bankv2.model.Card;
import com.myproject.bankv2.model.User;
import com.myproject.bankv2.repository.account.AccountRepository;
import com.myproject.bankv2.repository.user.UserRepository;
import com.myproject.bankv2.util.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Autowired
    public AccountService(AccountRepository repository, UserRepository userRepository) {
        this.accountRepository = repository;
        this.userRepository = userRepository;
    }

    public List<AccountDTO> getAccounts(){
        List<AccountDTO> result = new ArrayList<>();
        List<Account> accounts = accountRepository.getAllAccounts();

        accounts.forEach(account -> result.add(new AccountDTO(account)));
        if (!result.isEmpty()) {
            return result;
        }
        throw new AccountNotFoundException("No accounts in data base");
    }

    public List<AccountDTO> getByUser(String username){
        User user = userRepository.getUserByUsername(username);
        List<Account> accounts = accountRepository.getByUser(user);
        accounts.stream().forEach(account -> account.setAmount(account.getCards().stream().mapToDouble(Card::getAmount).sum()));
        List<AccountDTO> result = new ArrayList<>();
        accounts.forEach(account -> result.add(new AccountDTO(account)));
        return result;
    }

    public Account getAccountByAccountNumber(String accountNumber){
        Account result = accountRepository.getAccountByAccountNumber(accountNumber);

        if (result == null){
            throw new AccountNotFoundException("Account not found");
        }
        return result;
    }

    public AccountDTO getAccountById(int id){
        return new AccountDTO(accountRepository.getAccountById(id));
    }

    public void createAccount(Account account){
        User user = userRepository.findById(account.getUser().getId());
        if (user == null){
            throw new UserNotFoundException();
        }
        account.setUser(user);
        account.setCurrency(account.getCurrency().toUpperCase().substring(0,3));
        account.setAccountNumber(Utilities.generateBankAccount(account.getCurrency()));
        account.setAccountCreatedAt(LocalDateTime.now());
        if (account.getCards().isEmpty()){
            account.setAmount(0);
        }else {
            account.setAmount(account.getCards().stream().mapToDouble(Card::getAmount).sum());
        }
        Account searchAccount = accountRepository.getAccountByAccountNumber(account.getAccountNumber());;
        while (searchAccount != null){
            account.setAccountNumber(Utilities.generateBankAccount(account.getCurrency()));
            searchAccount = accountRepository.getAccountByAccountNumber(account.getAccountNumber());
        }
        accountRepository.saveAccount(account);
    }


    public void deleteAccount(String accountNumber){
        accountRepository.deleteAccount(
                accountRepository.getAccountByAccountNumber(accountNumber)
                        .getId()
        );
    }


}
