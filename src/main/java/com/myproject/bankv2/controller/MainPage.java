package com.myproject.bankv2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPage {

    @GetMapping
    public String welcome(){
        return "index";
    }
    @GetMapping("/login")
    public String showLogin(){
        return "login";
    }
}
