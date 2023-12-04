package br.com.gogoplay.app.infra.game.domain.entities;

import br.com.gogoplay.app.infra.game.infra.database.GameTypeDataBase;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record GameCreateDTO(
        String name,
        String description,
        BigDecimal cost,
        int prizeMultiplier,
        BigDecimal prize,
        GameTypeDataBase type,
        LocalDateTime initialDate,
        LocalDateTime finalDate,
        LocalDateTime drawDate
) {
}
