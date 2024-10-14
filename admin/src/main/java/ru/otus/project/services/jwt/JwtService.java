package ru.otus.project.services.jwt;

import ru.otus.project.models.User;

public interface JwtService {
    String extractUserName(String token);

    String generateToken(User user);

    boolean isTokenValid(String token, User user);
}
