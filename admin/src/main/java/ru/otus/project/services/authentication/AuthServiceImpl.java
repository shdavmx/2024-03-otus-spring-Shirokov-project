package ru.otus.project.services.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import ru.otus.project.models.User;
import ru.otus.project.models.requests.SignInRequest;
import ru.otus.project.models.requests.SignUpRequest;
import ru.otus.project.models.responses.JwtAuthResponse;
import ru.otus.project.services.jwt.JwtService;
import ru.otus.project.services.userdetails.ProjectUserDetails;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final ProjectUserDetails projectUserDetails;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthResponse signUp(SignUpRequest request) {
        User user = request.toUser();

        projectUserDetails.saveUser(user);

        String jwt = jwtService.generateToken(user);
        return new JwtAuthResponse(jwt);
    }

    @Override
    public JwtAuthResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        User user = (User)projectUserDetails.loadUserByUsername(request.getUsername());

        String jwt = jwtService.generateToken(user);
        return new JwtAuthResponse(jwt);
    }
}
