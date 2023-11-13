package br.com.gogoplay.app.implementation.games.animals.domain.entities;

import java.math.BigDecimal;
import java.util.UUID;

public record AnimalGameDTO(UUID user_id, String cpf, Animals bicho, BigDecimal aposta, int game_code) {
}
