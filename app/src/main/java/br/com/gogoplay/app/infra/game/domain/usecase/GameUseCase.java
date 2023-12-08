package br.com.gogoplay.app.infra.game.domain.usecase;

import br.com.gogoplay.app.infra.game.domain.entities.GameCreateDTO;
import br.com.gogoplay.app.infra.game.domain.entities.GameUpdateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface GameUseCase {
    ResponseEntity create(GameCreateDTO gameModel);

    ResponseEntity update(GameUpdateDTO gameModel);

    ResponseEntity listAllGame();

    ResponseEntity getGameByCode(int id);
}
