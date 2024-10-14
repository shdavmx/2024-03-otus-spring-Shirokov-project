package ru.otus.project.services.userdetails;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.otus.project.models.User;

public interface ProjectUserDetails extends UserDetailsService {
    boolean saveUser(User user);
}
