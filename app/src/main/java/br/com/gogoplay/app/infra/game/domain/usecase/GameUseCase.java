package br.com.gogoplay.app.infra.game.domain.usecase;

import br.com.gogoplay.app.infra.game.domain.entities.GameCreateDTO;
import jakarta.persistence.Id;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface GameUseCase {
    ResponseEntity create(GameCreateDTO gameModel);

    ResponseEntity listAllGame();

    ResponseEntity getGameByID(int id);
}
