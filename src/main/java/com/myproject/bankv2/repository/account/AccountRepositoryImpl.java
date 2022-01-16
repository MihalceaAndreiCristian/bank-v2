package com.myproject.bankv2.repository.account;

import com.myproject.bankv2.model.Account;
import com.myproject.bankv2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    private final AccountRepo accountRepo;

    @Autowired
    public AccountRepositoryImpl(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepo.findAll();
    }

    @Override
    public Account getAccountById(int id) {
        return accountRepo.findById(id);
    }

    @Override
    public List<Account> getByUser(User user) {
        return accountRepo.findByUser(user);
    }

    @Override
    public void saveAccount(Account account) {
        accountRepo.save(account);
    }

    @Override
    public void deleteAccount(int id) {
        accountRepo.deleteById(id);
    }

    @Override
    public Account getAccountByAccountNumber(String accountNumber) {
        return accountRepo.findByAccountNumber(accountNumber);
    }
}
