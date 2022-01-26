package com.myproject.bankv2.security.auth.repository.authorities;

import com.myproject.bankv2.security.auth.model.Authority;

public interface AuthoritiesRepository {

    void saveAuthority(Authority authority);
    void deleteAuthority(Authority authority);

}
