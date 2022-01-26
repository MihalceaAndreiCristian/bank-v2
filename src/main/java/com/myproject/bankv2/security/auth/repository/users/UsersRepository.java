package com.myproject.bankv2.security.auth.repository.users;

import com.myproject.bankv2.security.auth.model.Users;

public interface UsersRepository {
    void saveUsers(Users users);
    void deleteUsers(String username);
}
