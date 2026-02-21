package com.example.SmartAgriConnect.security;


import com.example.SmartAgriConnect.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SecurityUtils {



    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        if (principal instanceof User user) {
            return user;
        }

        throw new RuntimeException("Utilisateur non authentifié");
    }

    public String getEmail() {
        return getCurrentUser().getEmail();
    }

    public UUID getUserId() {
        return getCurrentUser().getId();
    }

    public String getRoles() {
        return getCurrentUser().getRole().name();
    }

    public UUID getAgriculteurId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User user) {
            return user.getAgriculteur() != null ? user.getAgriculteur().getId() : null;
        }
        return null;
    }


}
