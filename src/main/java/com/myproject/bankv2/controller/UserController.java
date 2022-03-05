package com.myproject.bankv2.controller;

import com.myproject.bankv2.dto.UserDTO;
import com.myproject.bankv2.model.User;
import com.myproject.bankv2.security.auth.model.Authority;
import com.myproject.bankv2.security.auth.model.Users;
import com.myproject.bankv2.security.auth.repository.users.UsersRepository;
import com.myproject.bankv2.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/user")
public class UserController {

    private final UserServiceImpl service;
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserServiceImpl service, UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/all")
    public String getAllUsers(Model model) {
        List<UserDTO> users = service.getUsers();

        model.addAttribute("users", users);
        return "user-list";
    }


    @GetMapping("/details")
    public String getUser(@RequestParam String username, Model model) {

        UserDTO user = service.getUserByUsername(username);
        model.addAttribute("user", user);
        return "user-details";

    }

    @GetMapping("/formForAdd")
    public String createUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register-form";
    }

    @PostMapping("/add")
    private String saveUser(@ModelAttribute("user") User user) {

        Users users = new Users();
        users.setUsername(user.getUsername());
        users.setPassword(passwordEncoder.encode(user.getPassword()));
        users.setEnabled(true);
        Authority authority = new Authority();
        List<Authority> authorities = new ArrayList<>();
        authority.setAuthority("USER");
        authority.setUsername(users);
        authorities.add(authority);
        users.setAuthority(authorities);
        usersRepository.saveUsers(users);
        service.saveUser(user);
        return "action-done";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("username") String username) {
        service.delete(username);
        return "action-done";
    }

    @GetMapping("/update")
    public String updateUserForm(@RequestParam("username") String username, Model model) {
        User user = service.getUser(username);
        model.addAttribute("user", user);
        return "update-user";
    }


    @PostMapping("/save")
    public String updateUserForm1(@ModelAttribute("user") User user) {
        service.updateUser(user);
        return "action-done";
    }
}
