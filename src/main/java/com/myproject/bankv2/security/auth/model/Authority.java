package com.myproject.bankv2.security.auth.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
@Data
@NoArgsConstructor
@IdClass(AuthorityId.class)
public class Authority {
    @Id
    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private Users username;

    @Id
    private String authority;

    public Authority(Users username, String authority) {
        this.username = username;
        this.authority = authority;
    }
}
