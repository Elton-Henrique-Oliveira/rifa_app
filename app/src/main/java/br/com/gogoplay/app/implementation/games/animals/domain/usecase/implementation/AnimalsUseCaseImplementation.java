package br.com.gogoplay.app.implementation.games.animals.domain.usecase.implementation;

import br.com.gogoplay.app.core.user.infraestructure.database.UserDataBase;
import br.com.gogoplay.app.core.user.infraestructure.database.implementation.UserRepository;
import br.com.gogoplay.app.implementation.games.animals.domain.entities.AnimalGameDTO;
import br.com.gogoplay.app.implementation.games.animals.domain.usecase.AnimalsUseCase;
import br.com.gogoplay.app.implementation.games.animals.infraestructure.database.AnimalsDataBase;
import br.com.gogoplay.app.implementation.games.animals.infraestructure.database.implementation.AnimalsRepository;
import br.com.gogoplay.app.implementation.games.infraestructure.database.GameDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AnimalsUseCaseImplementation implements AnimalsUseCase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnimalsRepository animalsRepository;


    public ResponseEntity create(AnimalGameDTO data) {

        Optional<UserDataBase> user = userRepository.findById(data.user().getId());

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
        }

        if (Integer.parseInt(data.bet() + "") <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
        }

        AnimalsDataBase bicho = new AnimalsDataBase(data.user(), data.cpf(), data.animal(), data.bet(), data.bet(), data.game());

        animalsRepository.save(bicho);

        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    public ResponseEntity listGames(){
        return ResponseEntity.status(HttpStatus.OK).body(animalsRepository.findAll());
    }
}
