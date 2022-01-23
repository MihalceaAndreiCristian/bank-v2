package com.myproject.bankv2.security.auth.repository.authorities;

import com.myproject.bankv2.security.auth.model.Authority;
import com.myproject.bankv2.security.auth.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthoritiesRepo extends JpaRepository<Authority,String> {

    void deleteByAuthority(String authority);
}
