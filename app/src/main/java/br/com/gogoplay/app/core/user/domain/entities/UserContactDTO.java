package br.com.gogoplay.app.core.user.domain.entities;

import br.com.gogoplay.app.core.user.infraestructure.database.UserContactTypeDataBase;

import java.util.UUID;

public record UserContactDTO(
        String label,

        UUID type
) {
}
