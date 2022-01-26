package com.myproject.bankv2.security.auth.repository.users;

import com.myproject.bankv2.security.auth.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<Users,String> {

    void deleteByUsername(String username);
    Users getByUsername(String username);
}
