package net.ru.voitekhov.notewebapp.controller;

import net.ru.voitekhov.notewebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@RequestMapping("/auth")
public class UserController {
    final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/success")

    public String getCategoryPage() {
        UserDetails principal =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        principal.getUsername();
        int id = service.findByEmail(principal.getUsername()).getId();
        return "redirect:/category/" + id;
    }
}
