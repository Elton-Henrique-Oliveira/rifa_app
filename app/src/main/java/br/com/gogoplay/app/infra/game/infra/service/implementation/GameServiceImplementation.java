package br.com.gogoplay.app.infra.game.infra.service.implementation;

import br.com.gogoplay.app.infra.game.domain.entities.GameCreateDTO;
import br.com.gogoplay.app.infra.game.domain.usecase.GameUseCase;
import br.com.gogoplay.app.infra.game.infra.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/game", produces = {"application/json"})
@Tag(name = "Gogo Play")
public class GameServiceImplementation implements GameService {

    @Autowired
    private GameUseCase gameUseCase;

    @Operation(summary = "Realiza a criação de um novo jogo", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criação do jogo feito com sucesso."),
            @ApiResponse(responseCode = "400", description = "Informações não foram preenchidas corretamente."),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado."),
            @ApiResponse(responseCode = "403", description = "Usuário sem permissão."),
            @ApiResponse(responseCode = "500", description = "Erro ao tentar criar jogo."),
    })
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(
            @RequestBody GameCreateDTO gameModel
    ) {
        return gameUseCase.create(gameModel);
    }
}