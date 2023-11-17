package br.com.gogoplay.app.core.user.domain.entities;

import br.com.gogoplay.app.core.user.infraestructure.database.ContactTypeDataBase;

public record UserContactDTO(
        String label,

        ContactTypeDataBase type
) {
}
