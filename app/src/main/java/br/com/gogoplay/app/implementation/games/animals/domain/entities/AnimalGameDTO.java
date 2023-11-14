package br.com.gogoplay.app.implementation.games.animals.domain.entities;

import br.com.gogoplay.app.core.user.infraestructure.database.UserDataBase;
import br.com.gogoplay.app.implementation.games.infraestructure.database.GameDataBase;

import java.math.BigDecimal;
import java.util.UUID;

public record AnimalGameDTO(UserDataBase user, String cpf, Animals animal, BigDecimal bet, GameDataBase game) {
}
