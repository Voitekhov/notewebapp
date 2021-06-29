package net.ru.voitekhov.notewebapp.controller;

import net.ru.voitekhov.notewebapp.exception.NotUniquEntityException;
import net.ru.voitekhov.notewebapp.model.User;
import net.ru.voitekhov.notewebapp.service.UserService;
import net.ru.voitekhov.notewebapp.util.login.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
        return "redirect:/category";
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
        if (!LoginUtil.confirmPassword(password, password_confirm)) {
            throw new NotUniquEntityException("Passwords are not equals");
        }
        if (!LoginUtil.isFreeEmail(email)) {
            throw new NotUniquEntityException(String.format("Email %s is busy", email));
        }
        service.save(new User(null, username, email, password));
        return getLoginPage();
    }

}

