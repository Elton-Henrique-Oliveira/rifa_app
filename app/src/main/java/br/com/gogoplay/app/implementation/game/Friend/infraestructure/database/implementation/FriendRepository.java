package br.com.gogoplay.app.implementation.game.Friend.infraestructure.database.implementation;

import br.com.gogoplay.app.implementation.game.Friend.infraestructure.database.FriendDataBase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FriendRepository extends JpaRepository<FriendDataBase, UUID> {
}
