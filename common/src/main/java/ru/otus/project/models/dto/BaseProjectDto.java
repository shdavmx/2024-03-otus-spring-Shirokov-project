package ru.otus.project.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BaseProjectDto {
    private int code;

    private boolean isFailed;

    private String ErrorText;
}
