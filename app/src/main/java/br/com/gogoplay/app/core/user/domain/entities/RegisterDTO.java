package br.com.gogoplay.app.core.user.domain.entities;

public record RegisterDTO(
        String login,
        String password,
        UserRole role,
        String name
) {
}
