package br.com.gogoplay.app.core.user.infra.service.implementation;

import br.com.gogoplay.app.core.user.domain.entities.AlterPasswordDTO;
import br.com.gogoplay.app.core.user.domain.entities.UserAlterDTO;
import br.com.gogoplay.app.core.user.domain.entities.UserCreateDTO;
import br.com.gogoplay.app.core.user.domain.usecase.implementation.UserUseCaseImplementation;
import br.com.gogoplay.app.core.user.infra.service.UserService;
import br.com.gogoplay.app.core.utils.TokenUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users", produces = {"application/json"})
@Tag(name = "Gogo Play")
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserUseCaseImplementation userUseCase;

    @Operation(summary = "Realiza a criação de um novo usuário", method = "POST")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "201", description = "Criação do usuário feito com sucesso."),
            @ApiResponse(responseCode = "400", description = "Informações não foram preenchidas corretamente."),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado."),
            @ApiResponse(responseCode = "403", description = "Usuário sem permissão."),
            @ApiResponse(responseCode = "500", description = "Erro ao tentar criar usuário."),
    })
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(
            @RequestBody UserCreateDTO userModel,
            @RequestHeader(name = "Authorization") String authorizationHeader
    ) {

        String authToken = TokenUtils.extractToken(authorizationHeader);
        return userUseCase.create(userModel, authToken);
    }

    @Operation(summary = "Realiza a alteração das informações de um usuário", method = "PUT")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Usuário foi alterado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Informações não foram preenchidas corretamente."),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado."),
            @ApiResponse(responseCode = "403", description = "Usuário sem permissão."),
            @ApiResponse(responseCode = "500", description = "Erro ao tentar alterar usuário."),
    })
    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(
            @RequestBody UserAlterDTO userModel,
            @RequestHeader(name = "Authorization") String authorizationHeader
    ) {

        String authToken = TokenUtils.extractToken(authorizationHeader);

        return userUseCase.update(userModel, authToken);
    }

    @Operation(summary = "Realiza a alteração de senha de um usuário", method = "POST")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Senha alterada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Informações não foram preenchidas corretamente."),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado."),
            @ApiResponse(responseCode = "403", description = "Usuário sem permissão."),
            @ApiResponse(responseCode = "500", description = "Erro ao tentar alterar senha."),
    })
    @PutMapping(value = "/alterPassword", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> alterPassword(
            @RequestBody AlterPasswordDTO alterPassword,
            @RequestHeader(name = "Authorization") String authorizationHeader
    ) {

        String authToken = TokenUtils.extractToken(authorizationHeader);

        return userUseCase.alterPassword(alterPassword, authToken);
    }
}
