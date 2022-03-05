package com.myproject.bankv2.repository;

import com.myproject.bankv2.model.Account;
import com.myproject.bankv2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Integer> {

    Account findById(int id);
    List<Account> findByUser(User user);
    Account findByAccountNumber(String accountNumber);
}
