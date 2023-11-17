package br.com.gogoplay.app.core.user.domain.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record UserCreateDTO(
        UUID id,
        LocalDate birthDate,
        String description,
        String name,
        String userName,
        String role,
        String login,
        String password,
        List<UserContactDTO> contacts
) {
}
