package br.com.gogoplay.app.implementation.game.infraestructure.database.implementation;

import br.com.gogoplay.app.implementation.game.infraestructure.database.GameDataBase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GameRepository extends JpaRepository<GameDataBase, UUID> {
}
