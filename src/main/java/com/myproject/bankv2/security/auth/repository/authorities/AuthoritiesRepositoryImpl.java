package com.myproject.bankv2.security.auth.repository.authorities;

import com.myproject.bankv2.security.auth.model.Authority;
import com.myproject.bankv2.security.auth.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AuthoritiesRepositoryImpl implements AuthoritiesRepository{

    private final AuthoritiesRepo authoritiesRepo;

    @Autowired
    public AuthoritiesRepositoryImpl(AuthoritiesRepo authoritiesRepo) {
        this.authoritiesRepo = authoritiesRepo;
    }

    @Override
    public void saveAuthority(Authority authority) {
        authoritiesRepo.save(authority);
    }

    @Override
    public void deleteAuthority(Authority authority) {
        authoritiesRepo.deleteByAuthority(authority.getAuthority());
    }

}
