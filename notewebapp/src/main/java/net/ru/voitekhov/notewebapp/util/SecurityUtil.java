package net.ru.voitekhov.notewebapp.util;

import net.ru.voitekhov.notewebapp.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

// Class to get the authenticated user
@Component
public class SecurityUtil {


    private static UserServiceImpl service;

    @Autowired
    private SecurityUtil(UserServiceImpl service) {
        SecurityUtil.service = service;
    }

    public static Integer getAuthUser() {
        UserDetails principal =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        principal.getUsername();
        return service.findByEmail(principal.getUsername()).getId();
    }
}
