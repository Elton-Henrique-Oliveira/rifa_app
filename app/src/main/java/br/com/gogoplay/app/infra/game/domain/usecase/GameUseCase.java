package br.com.gogoplay.app.infra.game.domain.usecase;

import br.com.gogoplay.app.infra.game.domain.entities.GameCreateDTO;
import org.springframework.http.ResponseEntity;

public interface GameUseCase {
    ResponseEntity create(GameCreateDTO gameModel);
}
