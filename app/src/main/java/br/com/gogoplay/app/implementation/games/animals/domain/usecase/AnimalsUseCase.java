package br.com.gogoplay.app.implementation.games.animals.domain.usecase;

import br.com.gogoplay.app.implementation.games.animals.domain.entities.AnimalGameDTO;
import org.springframework.http.ResponseEntity;

public interface AnimalsUseCase {
    ResponseEntity create(AnimalGameDTO data);

    ResponseEntity listGames();
}
