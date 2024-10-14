package ru.otus.project.models.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.otus.project.models.User;

@Data
public class SignUpRequest {
    @Size(min = 5, max = 500, message = "User name has length from 5 to 500")
    @NotBlank(message = "User name can not be empty")
    private  String username;

    @Size(min = 5, max = 500, message = "Email has length from 5 to 500")
    @NotBlank(message = "Email can not be empty")
    @Email(message = "Email has to format user@example.com")
    private  String email;

    @Size(min = 5, max = 500, message = "Password has length from 5 to 500")
    @NotBlank(message = "Password can not be empty")
    private  String password;

    public User toUser() {
        return new User(0, this.username, this.password, this.email, null);
    }
}
