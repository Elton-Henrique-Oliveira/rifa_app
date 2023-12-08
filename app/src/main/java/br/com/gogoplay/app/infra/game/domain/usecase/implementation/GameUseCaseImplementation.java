package br.com.gogoplay.app.infra.game.domain.usecase.implementation;

import br.com.gogoplay.app.infra.game.domain.entities.GameCreateDTO;
import br.com.gogoplay.app.infra.game.domain.entities.GameUpdateDTO;
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

        GameTypeDataBase gameType = null;
        List<String> errors = new ArrayList<>();

        if(gameModel == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(GAME_CREATE_NOT_INFORMED);
        }

        if (StringUtils.isBlank(gameModel.name())) {
            errors.add(NAME_NOT_INFORMED);
        }

        if (StringUtils.isBlank(gameModel.description())) {
            errors.add(DESCRIPTION_NOT_INFORMED);
        }

        if (gameModel.prizeMultiplier() <= 0 && gameModel.prize().compareTo(BigDecimal.ZERO) <= 0) {
            errors.add(BET_MULTIPLIER_OR_FINAL_PRIZE_NOT_INFORMED);
        }

        if (gameModel.type() == null || gameModel.type().getCode() == null) {
            errors.add(GAME_TYPE_NOT_INFORMED);
        }

        if (gameModel.type() != null && gameModel.type().getUuid() != null) {
            gameType = gameTypeRepository.findByUUID(gameModel.type().getUuid());
        }

        if (gameModel.type() != null && gameType == null) {
            errors.add(TYPE_NOT_REGISTERED);
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

        if (gameModel.finalDate() != null && gameModel.initialDate() != null && gameModel.finalDate().isBefore(gameModel.initialDate())) {
            errors.add(FINAL_DATE_IS_BEFORE_INITIAL_DATE);
        }

        if (gameModel.drawDate() != null && gameModel.initialDate() != null && gameModel.drawDate().isBefore(gameModel.initialDate())) {
            errors.add(DRAW_DATE_IS_BEFORE_INITIAL_DATE);
        }

        if (!errors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.join(", ", errors));
        }

        GameDataBase newGame = new GameDataBase(gameModel);

        gameRepository.save(newGame);

        return ResponseEntity.status(HttpStatus.CREATED).body(newGame);
    }

    public ResponseEntity update(
            GameUpdateDTO gameModel
    ){
        List<String> errors = new ArrayList<>();

        if(gameModel == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(GAME_UPDATE_NOT_INFORMED);
        }

        if(gameModel.code() <= 0){
            errors.add(GAME_CODE_GAS_TO_BE_GREATER_THAN_ZERO);
        }

        Optional<GameDataBase> gameVerify = this.gameRepository.findByCode(gameModel.code());

        if(gameVerify.isEmpty()){
            errors.add(GAME_NOT_FOUND_IN_DATABASE);
        }
        if (!errors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.join(", ", errors));
        }
        this.gameRepository.updateGameByCode(gameModel.name(), gameModel.description(), gameModel.cost(), gameModel.prizeMultiplier(), gameModel.prize(), gameModel.initialDate(), gameModel.finalDate(), gameModel.drawDate(), gameModel.code());
        Optional<GameDataBase> gameNow = this.gameRepository.findByCode(gameModel.code());

        return ResponseEntity.status(HttpStatus.OK).body(gameNow);
    }

    public ResponseEntity listAllGame() {

        List<GameDataBase> listGame = gameRepository.findAll();

        for(GameDataBase list : listGame){
            System.out.println(list);
        }

        if(listGame.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(listGame);
    }

    public ResponseEntity getGameByCode(int code) {

        Optional<GameDataBase> gameReturn = gameRepository.findByCode(code);

        if (gameReturn.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("Nenhum game encontrado para o Codigo: " + code);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(gameReturn);
        }
    }
}
