package com.myproject.bankv2.security.auth.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class Users {

    @Id
    @Column(name = "username", nullable = false)
    private String username;
    private String password;
    private boolean enabled;

    @OneToMany(mappedBy = "username", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Authority> authority;

    public Users(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }
}

