package net.ru.voitekhov.notewebapp.util.login;

import net.ru.voitekhov.notewebapp.repository.springdata.JpaUserRepository;
import org.springframework.stereotype.Component;

@Component
public class LoginUtil {

    private static JpaUserRepository userRepository;

    private LoginUtil(JpaUserRepository repository) {
        userRepository = repository;
    }

    public static boolean confirmPassword(String password1, String password2) {
        return password1.equals(password2);
    }

    public static boolean isFreeEmail(String email) {
        return userRepository.findByEmail(email) == null;
    }

}
