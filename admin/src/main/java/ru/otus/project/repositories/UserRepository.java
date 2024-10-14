package ru.otus.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.project.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String username);
}
