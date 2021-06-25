package net.ru.voitekhov.notewebapp.util.login;

import net.ru.voitekhov.notewebapp.repository.springdata.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginUtil {

    private final JpaUserRepository userRepository;

    @Autowired
    public LoginUtil(JpaUserRepository repository) {
        userRepository = repository;

    }

    public boolean confirmPassword(String password1, String password2) {
        return password1.equals(password2);
    }

    public boolean isFreeEmail(String email) {
        return userRepository.findByEmail(email) == null;
    }

}
