package br.com.gogoplay.app.infra.game.infra.database.implementation;

import br.com.gogoplay.app.infra.game.domain.entities.GameCreateDTO;
import br.com.gogoplay.app.infra.game.domain.entities.GameTypeCreateDTO;
import br.com.gogoplay.app.infra.game.domain.entities.GameUpdateDTO;
import br.com.gogoplay.app.infra.game.infra.database.GameDataBase;
import br.com.gogoplay.app.infra.game.infra.database.GameTypeDataBase;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
class GameRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    GameTypeRepository gameTypeRepository;

    @Test
    @DisplayName("Consulta um game que existe no banco de dados")
    void findByCodeCase1() {
        int code = 1;

        this.createGameType(new GameTypeCreateDTO(1, "Jogo do bicho"));
        Optional<GameTypeDataBase> gameTypeReturn = this.gameTypeRepository.findByCode(code);

        if (gameTypeReturn.isPresent()) {
            GameCreateDTO data = new GameCreateDTO("Elton", "Teste criado com sucesso", new BigDecimal(10), 15, new BigDecimal(0), gameTypeReturn.get(), LocalDateTime.now().plusHours(10), LocalDateTime.now().plusHours(100), LocalDateTime.now().plusHours(500));
            this.createGame(data);

            Optional<GameDataBase> result = this.gameRepository.findByCode(code);

            assertThat(result.isPresent()).isTrue();
        }
    }

    @Test
    @DisplayName("Consulta um game que não existe no banco de dados")
    void findByCodeCase2() {
        int code = 1;

        Optional<GameDataBase> result = this.gameRepository.findByCode(code);
        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Faz alteração do game no banco de dados por Code")
    void updateGameByCodeCase1(){
        assertDoesNotThrow(() -> this.gameRepository.updateGameByCode("Elton", "Elton testando", new BigDecimal(45), 9, new BigDecimal(0), LocalDateTime.now(), LocalDateTime.now().minusDays(1), LocalDateTime.now().minusDays(2), 1),
                "O método updateGameByCode deve ser executado sem lançar exceções");
    }

    private GameDataBase createGame(GameCreateDTO gameDTO) {
        GameDataBase newGame = new GameDataBase(gameDTO);
        this.entityManager.persist(newGame);

        return newGame;
    }

    private GameTypeDataBase createGameType(GameTypeCreateDTO gameTypeDTO) {
        GameTypeDataBase newGameType = new GameTypeDataBase(gameTypeDTO);
        this.entityManager.persist(newGameType);

        return newGameType;
    }
}