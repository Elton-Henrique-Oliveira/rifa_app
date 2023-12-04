package br.com.gogoplay.app.infra.game.infra.database.implementation;

import br.com.gogoplay.app.infra.game.infra.database.GameTypeDataBase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GameTypeRepository extends JpaRepository<GameTypeDataBase, UUID> {
}
