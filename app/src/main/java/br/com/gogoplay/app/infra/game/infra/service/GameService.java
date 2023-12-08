package br.com.gogoplay.app.infra.game.infra.service;

import br.com.gogoplay.app.infra.game.domain.entities.GameCreateDTO;
import br.com.gogoplay.app.infra.game.domain.entities.GameUpdateDTO;
import jakarta.persistence.Id;
import org.springframework.http.ResponseEntity;

public interface GameService {

    ResponseEntity<String> create(
            GameCreateDTO gameModel
    );

    ResponseEntity<String> update (
            GameUpdateDTO gameModel
    );

    ResponseEntity<String> deleteByCode(
            int code
    );

    ResponseEntity<String> listAllGame();

    ResponseEntity<String> getGameByCode(
            int code
    );
}
