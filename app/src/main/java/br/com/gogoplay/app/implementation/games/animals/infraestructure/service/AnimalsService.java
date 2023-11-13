package br.com.gogoplay.app.implementation.games.animals.infraestructure.service;


import br.com.gogoplay.app.implementation.games.animals.domain.entities.AnimalGameDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AnimalsService {

    ResponseEntity register(
            @RequestBody @Valid AnimalGameDTO data
    );

    ResponseEntity listGames();
}
