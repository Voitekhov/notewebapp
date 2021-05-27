package net.ru.voitekhov.notewebapp.controller;

import net.ru.voitekhov.notewebapp.model.User;
import net.ru.voitekhov.notewebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class AuthController {

    final UserService service;

    @Autowired
    public AuthController(UserService service) {
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

    @GetMapping("/registration")
    public String getRegistrationForm() {
        return "registration";
    }

    @PostMapping("/registration/check")
    public String registration(@RequestParam("username") String username,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password,
                               @RequestParam("password_confirm") String password_confirm) {
        if (service.confirmPassword(password, password_confirm) && service.isFreeEmail(email)) {
            service.save(new User(null, username, email, password));
            return getLoginPage();
        }

        return getRegistrationForm();
    }
}

