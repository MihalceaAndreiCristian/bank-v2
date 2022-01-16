package com.myproject.bankv2.repository.user;

import com.myproject.bankv2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {

    User findByUsername(String username);
    User findByEmail(String email);
    User findById(int id);
}
