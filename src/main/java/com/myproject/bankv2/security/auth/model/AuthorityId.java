package com.myproject.bankv2.security.auth.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@Data
public class AuthorityId implements Serializable {
    private Users username;
    private String authority;

    public AuthorityId(Users username, String authority) {
        this.username = username;
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorityId that = (AuthorityId) o;
        return username.equals(that.username) && authority.equals(that.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, authority);
    }
}
