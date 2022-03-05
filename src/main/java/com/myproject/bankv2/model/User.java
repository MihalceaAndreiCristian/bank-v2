package com.myproject.bankv2.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Data
@NoArgsConstructor
@Table(name = "userss")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    private String username;

    private String password;

    @NotBlank(message = "Provide a email")
    @Pattern(regexp = "([a-zA-Z0-9-_.])+@[a-z]+\\.com")
    private String email;

    private String firstName;

    private String lastName;
    private String fullName;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    private LocalDateTime userCreatedAt;

    @OneToMany(mappedBy = "user",cascade = ALL)
    private List<Account> accounts;

    @OneToMany(mappedBy = "user",cascade = ALL)
    private List<Card> cards;


    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;

    }


}
