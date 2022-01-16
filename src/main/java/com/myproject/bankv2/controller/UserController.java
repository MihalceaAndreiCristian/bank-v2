package com.myproject.bankv2.controller;

import com.myproject.bankv2.dto.UserDTO;
import com.myproject.bankv2.model.User;
import com.myproject.bankv2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public String getAllUsers(Model model) {
        List<UserDTO> users =  service.getUsers();

        model.addAttribute("users",users);
        return "user-list";
    }


    @GetMapping("/details")
    public String getUser(@RequestParam String username, Model model) {

        UserDTO user = service.getUserByUsername(username);
        model.addAttribute("user",user);
        return "user-details";

    }

    @GetMapping("/formForAdd")
    public String createUser(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "register-form";
    }

    @PostMapping("/add")
    private String saveUser(@ModelAttribute("user") User user){
        service.saveUser(user);
        return "action-done";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("username") String username) {
        service.delete(username);
        return "action-done";
    }

    @GetMapping("/update")
    public String updateUserForm(@RequestParam("username") String username,Model model){
        User user = service.getUser(username);
        model.addAttribute("user",user);
        return "update-user";
    }



    @PostMapping("/save")
    public String updateUserForm1(@ModelAttribute("user") User user){
        service.updateUser(user);
        return "action-done";
    }
}
