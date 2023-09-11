package com.dgliger.utility;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@UtilityClass
public class CurrentUserService {
    static final String NO_LOGGED_USER = "No logged in user";

    public String getLoggedUserName() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new RuntimeException(NO_LOGGED_USER));
    }
}
