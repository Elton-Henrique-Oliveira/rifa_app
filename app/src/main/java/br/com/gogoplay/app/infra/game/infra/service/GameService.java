package br.com.gogoplay.app.infra.game.infra.service;

import br.com.gogoplay.app.infra.game.domain.entities.GameCreateDTO;
import org.springframework.http.ResponseEntity;

public interface GameService {

    ResponseEntity<String> create(
            GameCreateDTO gameModel
    );
}
