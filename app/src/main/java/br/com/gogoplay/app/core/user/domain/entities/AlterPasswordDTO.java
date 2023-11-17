package br.com.gogoplay.app.core.user.domain.entities;

public record AlterPasswordDTO(
        String oldPassword,
        String newPassword
) {
}
