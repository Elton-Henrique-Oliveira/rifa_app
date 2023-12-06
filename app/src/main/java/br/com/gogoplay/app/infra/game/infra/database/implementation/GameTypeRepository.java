package br.com.gogoplay.app.infra.game.infra.database.implementation;

import br.com.gogoplay.app.core.user.infra.database.UserDataBase;
import br.com.gogoplay.app.infra.game.infra.database.GameTypeDataBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface GameTypeRepository extends JpaRepository<GameTypeDataBase, UUID> {

    @Query("SELECT gt FROM game_type gt WHERE gt.uuid = :uuid")
    GameTypeDataBase findByUUID(UUID uuid);

    Optional<GameTypeDataBase> findByCode(int code);

    Optional<GameTypeDataBase> findFirstByCode(int code);
}
