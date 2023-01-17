package com.softserve.itacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class LoginController {

    @GetMapping("/form-login")
    public String login() {
        return "form-login";
    }

//    @PostMapping("/perform-logout")
//    public String logout() {
//        return "redirect:/form-login?logout=true";
//    }
}
