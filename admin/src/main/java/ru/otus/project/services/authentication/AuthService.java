package ru.otus.project.services.authentication;

import ru.otus.project.models.requests.SignInRequest;
import ru.otus.project.models.requests.SignUpRequest;
import ru.otus.project.models.responses.JwtAuthResponse;

public interface AuthService {
    JwtAuthResponse signUp(SignUpRequest request);

    JwtAuthResponse signIn(SignInRequest request);
}
