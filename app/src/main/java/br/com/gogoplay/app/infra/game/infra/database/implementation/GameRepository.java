package br.com.gogoplay.app.infra.game.infra.database.implementation;

import br.com.gogoplay.app.infra.game.infra.database.GameDataBase;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Transactional
public interface GameRepository extends JpaRepository<GameDataBase, Id> {
    Optional<GameDataBase> findByCode(int code);

    @Modifying
    @Transactional
    @Query("UPDATE game g SET g.name = :name, g.description = :description, g.cost = :cost, g.prizeMultiplier = :prizeMultiplier, g.prize = :prize, g.initialDate = :initialDate, g.finalDate = :finalDate, g.drawDate = :drawDate WHERE g.code = :code")
    void updateGameByCode(String name,
                    String description,
                    BigDecimal cost,
                    int prizeMultiplier,
                    BigDecimal prize,
                    LocalDateTime initialDate,
                    LocalDateTime finalDate,
                    LocalDateTime drawDate, int code
    );
}
