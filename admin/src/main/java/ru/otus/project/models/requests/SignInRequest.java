package ru.otus.project.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignInRequest {
    @Size(min = 5, max = 500, message = "User name has length from 5 to 500")
    @NotBlank(message = "User name can not be empty")
    private  String username;

    @Size(min = 5, max = 500, message = "Password has length from 5 to 500")
    @NotBlank(message = "Password can not be empty")
    private  String password;
}
