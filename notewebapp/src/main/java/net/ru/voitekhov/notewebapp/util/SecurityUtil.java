package net.ru.voitekhov.notewebapp.util;

import net.ru.voitekhov.notewebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

// Class to get the authenticated user
@Component
public class SecurityUtil {


    private static UserService service;

    @Autowired
    private SecurityUtil(UserService service) {
        SecurityUtil.service = service;
    }

    public static Integer getAuthUser() {
        UserDetails principal =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        principal.getUsername();
        return service.findByEmail(principal.getUsername()).getId();
    }
}
