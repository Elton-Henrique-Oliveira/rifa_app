package br.com.gogoplay.app.infra.game.infra.database.implementation;

import br.com.gogoplay.app.infra.game.infra.database.GameDataBase;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface GameRepository extends JpaRepository<GameDataBase, Id> {
    Optional<GameDataBase> findByCode(int code);
}
