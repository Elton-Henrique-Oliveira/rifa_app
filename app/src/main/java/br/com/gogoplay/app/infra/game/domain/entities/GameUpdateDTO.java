package br.com.gogoplay.app.infra.game.domain.entities;

import br.com.gogoplay.app.infra.game.infra.database.GameTypeDataBase;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record GameUpdateDTO(
        int code,
        String name,
        String description,
        BigDecimal cost,
        int prizeMultiplier,
        BigDecimal prize,
        LocalDateTime initialDate,
        LocalDateTime finalDate,
        LocalDateTime drawDate
) {
}
