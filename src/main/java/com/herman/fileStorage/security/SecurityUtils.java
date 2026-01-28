package com.herman.fileStorage.security;

import com.herman.fileStorage.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static User getAuthenticatedUser() {
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
