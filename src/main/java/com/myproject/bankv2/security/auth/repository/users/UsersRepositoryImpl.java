package com.myproject.bankv2.security.auth.repository.users;

import com.myproject.bankv2.security.auth.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsersRepositoryImpl implements UsersRepository {

    private final UsersRepo usersRepo;

    @Autowired
    public UsersRepositoryImpl(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    @Override
    public void saveUsers(Users users) {
        usersRepo.save(users);
    }

    @Override
    public void deleteUsers(String username) {
        usersRepo.deleteByUsername(username);
    }
}
