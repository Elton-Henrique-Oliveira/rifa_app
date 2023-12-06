package br.com.gogoplay.app.infra.game.domain.usecase.implementation;

import br.com.gogoplay.app.infra.game.domain.entities.GameCreateDTO;
import br.com.gogoplay.app.infra.game.domain.usecase.GameUseCase;
import br.com.gogoplay.app.infra.game.infra.database.GameDataBase;
import br.com.gogoplay.app.infra.game.infra.database.GameTypeDataBase;
import br.com.gogoplay.app.infra.game.infra.database.implementation.GameRepository;
import br.com.gogoplay.app.infra.game.infra.database.implementation.GameTypeRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.gogoplay.app.infra.game.domain.errors.GameErrors.*;

@Service
public class GameUseCaseImplementation implements GameUseCase {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameTypeRepository gameTypeRepository;

    @Override
    public ResponseEntity create(GameCreateDTO gameModel) {

        List<String> errors = new ArrayList<>();

        if (StringUtils.isBlank(gameModel.name())) {
            errors.add(NAME_NOT_INFORMED);
        }

        if (StringUtils.isBlank(gameModel.description())) {
            errors.add(DESCRIPTION_NOT_INFORMED);
        }

        if (gameModel.prizeMultiplier() <= 0 && gameModel.prize().compareTo(BigDecimal.ZERO) <= 0) {
            errors.add(BET_MULTIPLIER_OR_FINAL_PRIZE_NOT_INFORMED);
        }

        if (gameModel.type().getUuid() == null) {
            errors.add(GAME_TYPE_NOT_INFORMED);
        }

        if (gameModel.initialDate() == null) {
            errors.add(INITIAL_DATE_NOT_INFORMED);
        }

        if (gameModel.finalDate() == null) {
            errors.add(FINAL_DATE_NOT_INFORMED);
        }

        if (gameModel.drawDate() == null) {
            errors.add(DRAW_DATE_NOT_INFORMED);
        }

        if (gameModel.type().getUuid() == null) {
            errors.add(GAME_TYPE_NOT_INFORMED);
        }

        GameTypeDataBase gameType = gameTypeRepository.findByUUID(gameModel.type().getUuid());
        if (gameType == null) {
            errors.add(TYPE_NOT_REGISTERED);
        }

        System.out.println(gameModel.type().getUuid());

        assert gameModel.finalDate() != null;
        if (gameModel.finalDate().isBefore(gameModel.initialDate())) {
            errors.add(FINAL_DATE_IS_BEFORE_INITIAL_DATE);
        }

        assert gameModel.drawDate() != null;
        if (gameModel.drawDate().isBefore(gameModel.initialDate())) {
            errors.add(DRAW_DATE_IS_BEFORE_INITIAL_DATE);
        }

        if (!errors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.join(", ", errors));
        }

        GameDataBase newGame = new GameDataBase(gameModel);

        gameRepository.save(newGame);

        return ResponseEntity.status(HttpStatus.CREATED).body(newGame);
    }

    public ResponseEntity listAllGame() {

        List<GameDataBase> listGame = new ArrayList<>();

        listGame = gameRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(listGame);
    }

    public ResponseEntity getGameByID(int code) {

        Optional<GameDataBase> gameReturn = gameRepository.findByCode(code);

        if (gameReturn.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("Nenhum game encontrado para o Codigo: " + code);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(gameReturn);
        }
    }
}
