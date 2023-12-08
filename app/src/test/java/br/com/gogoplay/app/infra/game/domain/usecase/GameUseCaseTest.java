package br.com.gogoplay.app.infra.game.domain.usecase;

import br.com.gogoplay.app.infra.game.domain.entities.GameCreateDTO;
import br.com.gogoplay.app.infra.game.domain.entities.GameTypeCreateDTO;
import br.com.gogoplay.app.infra.game.domain.usecase.implementation.GameUseCaseImplementation;
import br.com.gogoplay.app.infra.game.infra.database.GameDataBase;
import br.com.gogoplay.app.infra.game.infra.database.GameTypeDataBase;
import br.com.gogoplay.app.infra.game.infra.database.implementation.GameRepository;
import br.com.gogoplay.app.infra.game.infra.database.implementation.GameTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static br.com.gogoplay.app.infra.game.domain.errors.GameErrors.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class GameUseCaseTest {

    @Mock
    private GameRepository gameRepository;

    @Autowired
    private GameTypeRepository gameTypeRepository;

    @Autowired
    @InjectMocks
    private GameUseCaseImplementation gameUseCase;

    @BeforeEach
    void setup() {
        this.gameTypeRepository.save(new GameTypeDataBase(new GameTypeCreateDTO(5, "Jogo do bicho")));

        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Verifica erro caso não tena nenhum game passado para ser criado")
    void createCase0() {
        ResponseEntity response = gameUseCase.create(null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        assertEquals(response.getBody(), GAME_CREATE_NOT_INFORMED);
    }

    @Test
    @DisplayName("Caso a inclusão ocorra corretamente")
    void createCase1() {

        Optional<GameTypeDataBase> gameTypeReturn = this.gameTypeRepository.findFirstByCode(5);

        GameCreateDTO data = new GameCreateDTO("Elton", "Teste criado com sucesso", new BigDecimal(10), 15, new BigDecimal(0), gameTypeReturn.get(), LocalDateTime.now().plusHours(10), LocalDateTime.now().plusHours(100), LocalDateTime.now().plusHours(500));

        ResponseEntity response = gameUseCase.create(data);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @DisplayName("Verifica o erro se nome não for informado")
    void createCase2() {

        Optional<GameTypeDataBase> gameTypeReturn = this.gameTypeRepository.findFirstByCode(5);

        GameCreateDTO data = new GameCreateDTO("", "Teste criado com sucesso", new BigDecimal(10), 15, new BigDecimal(0), gameTypeReturn.get(), LocalDateTime.now().plusHours(10), LocalDateTime.now().plusHours(100), LocalDateTime.now().plusHours(500));

        ResponseEntity response = gameUseCase.create(data);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        assertEquals(response.getBody(), NAME_NOT_INFORMED);
    }

    @Test
    @DisplayName("Verifica o erro se descrição não for informado")
    void createCase3() {

        Optional<GameTypeDataBase> gameTypeReturn = this.gameTypeRepository.findFirstByCode(5);

        GameCreateDTO data = new GameCreateDTO("Elton", "", new BigDecimal(10), 15, new BigDecimal(0), gameTypeReturn.get(), LocalDateTime.now().plusHours(10), LocalDateTime.now().plusHours(100), LocalDateTime.now().plusHours(500));

        ResponseEntity response = gameUseCase.create(data);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        assertEquals(response.getBody(), DESCRIPTION_NOT_INFORMED);
    }

    @Test
    @DisplayName("Verifica o erro se multiplicador e premio final não for informado")
    void createCase4() {

        Optional<GameTypeDataBase> gameTypeReturn = this.gameTypeRepository.findFirstByCode(5);

        GameCreateDTO data = new GameCreateDTO("Elton", "Teste criado com sucesso", new BigDecimal(10), 0, new BigDecimal(0), gameTypeReturn.get(), LocalDateTime.now().plusHours(10), LocalDateTime.now().plusHours(100), LocalDateTime.now().plusHours(500));

        ResponseEntity response = gameUseCase.create(data);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        assertEquals(response.getBody(), BET_MULTIPLIER_OR_FINAL_PRIZE_NOT_INFORMED);
    }

    @Test
    @DisplayName("Verifica o erro se tipo de jogo não for informado")
    void createCase5() {
        GameCreateDTO data = new GameCreateDTO("Elton", "Teste criado com sucesso", new BigDecimal(10), 15, new BigDecimal(0), null, LocalDateTime.now().plusHours(10), LocalDateTime.now().plusHours(100), LocalDateTime.now().plusHours(500));

        ResponseEntity response = gameUseCase.create(data);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        assertEquals(response.getBody(), GAME_TYPE_NOT_INFORMED);
    }

    @Test
    @DisplayName("Verifica o erro se data inicial não for informado")
    void createCase6() {

        Optional<GameTypeDataBase> gameTypeReturn = this.gameTypeRepository.findFirstByCode(5);

        GameCreateDTO data = new GameCreateDTO("Elton", "Teste criado com sucesso", new BigDecimal(10), 15, new BigDecimal(0), gameTypeReturn.get(), null, LocalDateTime.now().plusHours(100), LocalDateTime.now().plusHours(500));

        ResponseEntity response = gameUseCase.create(data);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        assertEquals(response.getBody(), INITIAL_DATE_NOT_INFORMED);
    }

    @Test
    @DisplayName("Verifica o erro se data final não for informado")
    void createCase7() {

        Optional<GameTypeDataBase> gameTypeReturn = this.gameTypeRepository.findFirstByCode(5);

        GameCreateDTO data = new GameCreateDTO("Elton", "Teste criado com sucesso", new BigDecimal(10), 15, new BigDecimal(0), gameTypeReturn.get(), LocalDateTime.now().plusHours(10), null, LocalDateTime.now().plusHours(500));

        ResponseEntity response = gameUseCase.create(data);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        assertEquals(response.getBody(), FINAL_DATE_NOT_INFORMED);
    }

    @Test
    @DisplayName("Verifica o erro se data sorteio não for informado")
    void createCase8() {

        Optional<GameTypeDataBase> gameTypeReturn = this.gameTypeRepository.findFirstByCode(5);

        GameCreateDTO data = new GameCreateDTO("Elton", "Teste criado com sucesso", new BigDecimal(10), 15, new BigDecimal(0), gameTypeReturn.get(), LocalDateTime.now().plusHours(10), LocalDateTime.now().plusHours(100), null);

        ResponseEntity response = gameUseCase.create(data);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        assertEquals(response.getBody(), DRAW_DATE_NOT_INFORMED);
    }

    @Test
    @DisplayName("Verifica o erro se tipo de jogo não existe no banco de dados")
    void createCase9() {
        GameCreateDTO data = new GameCreateDTO("Elton", "Teste criado com sucesso", new BigDecimal(10), 15, new BigDecimal(0), new GameTypeDataBase(new GameTypeCreateDTO(555, "Jogo do bicho fake")), LocalDateTime.now().plusHours(10), LocalDateTime.now().plusHours(100), LocalDateTime.now().plusHours(500));

        ResponseEntity response = gameUseCase.create(data);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        assertEquals(response.getBody(), TYPE_NOT_REGISTERED);
    }

    @Test
    @DisplayName("Verifica o erro se data final for antes da inicial")
    void createCase10() {

        Optional<GameTypeDataBase> gameTypeReturn = this.gameTypeRepository.findFirstByCode(5);

        GameCreateDTO data = new GameCreateDTO("Elton", "Teste criado com sucesso", new BigDecimal(10), 15, new BigDecimal(0), gameTypeReturn.get(), LocalDateTime.now().plusHours(10).plusHours(200), LocalDateTime.now().plusHours(100), LocalDateTime.now().plusHours(500));

        ResponseEntity response = gameUseCase.create(data);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        assertEquals(response.getBody(), FINAL_DATE_IS_BEFORE_INITIAL_DATE);
    }

    @Test
    @DisplayName("Verifica o erro se data do sorteio for antes da inicial")
    void createCase11() {

        Optional<GameTypeDataBase> gameTypeReturn = this.gameTypeRepository.findFirstByCode(5);

        GameCreateDTO data = new GameCreateDTO("Elton", "Teste criado com sucesso", new BigDecimal(10), 15, new BigDecimal(0), gameTypeReturn.get(), LocalDateTime.now().plusHours(10).plusHours(600), LocalDateTime.now().plusHours(700), LocalDateTime.now().plusHours(500));

        ResponseEntity response = gameUseCase.create(data);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        assertEquals(response.getBody(), DRAW_DATE_IS_BEFORE_INITIAL_DATE);
    }

    @Test
    @DisplayName("Verifica se trouxe resultado na consulta")
    void listAllGameCase1() {
        Optional<GameTypeDataBase> gameTypeReturn = this.gameTypeRepository.findFirstByCode(5);
        GameDataBase data1 = new GameDataBase(new GameCreateDTO("Elton", "Teste criado com sucesso", new BigDecimal(10), 15, new BigDecimal(0), gameTypeReturn.get(), LocalDateTime.now().plusHours(10).plusHours(600), LocalDateTime.now().plusHours(700), LocalDateTime.now().plusHours(500)));
        GameDataBase data2 = new GameDataBase(new GameCreateDTO("Elton", "Teste criado com sucesso", new BigDecimal(10), 15, new BigDecimal(0), gameTypeReturn.get(), LocalDateTime.now().plusHours(10).plusHours(600), LocalDateTime.now().plusHours(700), LocalDateTime.now().plusHours(500)));
        GameDataBase data3 = new GameDataBase(new GameCreateDTO("Elton", "Teste criado com sucesso", new BigDecimal(10), 15, new BigDecimal(0), gameTypeReturn.get(), LocalDateTime.now().plusHours(10).plusHours(600), LocalDateTime.now().plusHours(700), LocalDateTime.now().plusHours(500)));

        when(gameRepository.findAll()).thenReturn(Arrays.asList(data1, data2, data3));

        ResponseEntity response = this.gameUseCase.listAllGame();

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Verifica se irá trazer uma lista vazia quando não encontrar nenhum resultado")
    void listAllGameCase2() {
        when(gameRepository.findAll()).thenReturn(Collections.emptyList());

        var response = this.gameUseCase.listAllGame();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        verify(this.gameRepository, times(1)).findAll();
    }
}