package net.ru.voitekhov.notewebapp.util;

import net.ru.voitekhov.notewebapp.repository.springdata.JpaUserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

// Class to get the authenticated user
@Component
public class SecurityUtil {

    private static JpaUserRepository userRepository;

    private SecurityUtil(JpaUserRepository repository) {
        userRepository = repository;
    }

    public static Integer getAuthUser() {
        UserDetails principal =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        principal.getUsername();
        return userRepository.findByEmail(principal.getUsername()).getId();
    }
}
