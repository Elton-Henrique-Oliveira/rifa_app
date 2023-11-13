package br.com.gogoplay.app.implementation.games.animals.infraestructure.service.implementation;

import br.com.gogoplay.app.implementation.games.animals.domain.entities.AnimalGameDTO;
import br.com.gogoplay.app.implementation.games.animals.domain.usecase.implementation.AnimalsUseCaseImplementation;
import br.com.gogoplay.app.implementation.games.animals.infraestructure.service.AnimalsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bicho")
public class AnimalsServiceImplementation implements AnimalsService {

    @Autowired
    private AnimalsUseCaseImplementation bichoUseCase;

    @PostMapping("/")
    public ResponseEntity register(@RequestBody @Valid AnimalGameDTO data) {
        return bichoUseCase.create(data);
    }

    @GetMapping("/")
    public ResponseEntity listGames(){
        return bichoUseCase.listGames();
    }
}
