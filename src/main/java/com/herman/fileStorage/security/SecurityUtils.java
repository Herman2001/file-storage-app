package com.herman.fileStorage.security;

import com.herman.fileStorage.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static User getAuthenticatedUser() {
        var authentications = SecurityContextHolder.getContext().getAuthentication();

        if (authentications == null || !authentications.isAuthenticated()) {
            throw new RuntimeException("User is not authenticated");
        }

        Object principal = authentications.getPrincipal();

        if (!(principal instanceof User user)) {
            throw new RuntimeException("User is not authenticated");
        }

        return user;
    }
}
