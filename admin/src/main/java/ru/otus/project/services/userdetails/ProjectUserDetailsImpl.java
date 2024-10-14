package ru.otus.project.services.userdetails;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.otus.project.models.Authority;
import ru.otus.project.models.AuthorityNames;
import ru.otus.project.models.User;
import ru.otus.project.models.enums.AuditTypes;
import ru.otus.project.repositories.AuthorityRepository;
import ru.otus.project.repositories.UserRepository;
import ru.otus.project.services.audit.AuditService;
import ru.otus.project.services.base.BaseService;

import java.util.List;

@Service
public class ProjectUserDetailsImpl extends BaseService implements ProjectUserDetails {
    private static final String USER_NOT_FOUND_EXCEPTION = "User name '%s' not found";
    private static final String USER_EXISTS_EXCEPTION = "User name '%s' already exist";
    private static final String USER_SAVED = "User '%s' saved successfully";

    private final Logger logger = LoggerFactory.getLogger(ProjectUserDetailsImpl.class);

    private final UserRepository userRepository;

    private final AuthorityRepository authorityRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private AuditService auditService;

    public ProjectUserDetailsImpl(UserRepository userRepository,
                                  AuthorityRepository authorityRepository,
                                  BCryptPasswordEncoder bCryptPasswordEncoder,
                                  AuditService auditService) {
        super(auditService);

        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public boolean saveUser(User user) {
        User existingUser = userRepository.findByUserName(user.getUsername());
        if (existingUser != null) {
            serviceLogging(logger, 400, USER_EXISTS_EXCEPTION.formatted(user.getUsername()));
            throw new UsernameNotFoundException(USER_EXISTS_EXCEPTION.formatted(user.getUsername()));
        }

        if (user.getAuthorities() == null || user.getAuthorities().isEmpty()) {
            Authority userAuthority = authorityRepository.findByName(AuthorityNames.ROLE_USER.name());
            user.setUserAuthorities(List.of(userAuthority));
            serviceLogging(logger, 100,
                    "No one authority was found for User '%s'. ".formatted(user.getUsername()) +
                            "Added default authority '%s'".formatted(userAuthority.getAuthority()));
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        serviceLogging(logger, 100, USER_SAVED.formatted(user.getUsername()));
        addAuditLog(AuditTypes.ADD.name(), "Admin", ProjectUserDetailsImpl.class.getName(),
                "saveUser", USER_SAVED.formatted(user.getUsername()));

        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
           throw new UsernameNotFoundException(USER_NOT_FOUND_EXCEPTION.formatted(username));
        }

        return user;
    }
}
