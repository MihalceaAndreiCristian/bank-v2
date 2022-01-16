package com.myproject.bankv2.repository.user;

import com.myproject.bankv2.model.User;

import java.util.List;

public interface UserRepository {

    List<User> getAllUsers();
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    User findById(int id);
    void saveUser(User user);
    void deleteUser(int id);

}
