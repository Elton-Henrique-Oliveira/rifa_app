package br.com.gogoplay.app.core.user.domain.entities;

import java.time.LocalDate;
import java.util.UUID;

public record UserAlterDTO(
        UUID id,
        LocalDate birthDate,
        String description,
        String email,
        String name,
        String userName,
        String role
) {
}
