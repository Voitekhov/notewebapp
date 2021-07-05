package net.ru.voitekhov.notewebapp.controller;

import net.ru.voitekhov.notewebapp.config.security.jwt.JwtTokenProvider;
import net.ru.voitekhov.notewebapp.exception.NotUniquEntityException;
import net.ru.voitekhov.notewebapp.model.LoginUser;
import net.ru.voitekhov.notewebapp.model.User;
import net.ru.voitekhov.notewebapp.service.UserService;
import net.ru.voitekhov.notewebapp.util.login.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


//TODO transform to rest controller
@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService service;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;


    @Autowired
    public AuthController(UserService service, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity getLoginPage(@RequestBody LoginUser request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            User user = service.findByEmail(request.getEmail());
            String token = jwtTokenProvider.createToken(request.getEmail(), user.getRole());
            Map<Object, Object> response = new HashMap<>();
            response.put("email", request.getEmail());
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
        }
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
        return "s";
    }

}

