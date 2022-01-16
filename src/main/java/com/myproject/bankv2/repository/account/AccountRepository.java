package com.myproject.bankv2.repository.account;

import com.myproject.bankv2.model.Account;
import com.myproject.bankv2.model.User;

import java.util.List;

public interface AccountRepository {

    List<Account> getAllAccounts();
    Account getAccountById(int id);
    List<Account> getByUser(User user);
    void saveAccount(Account account);
    void deleteAccount(int id);
    Account getAccountByAccountNumber(String accountNumber);
}
