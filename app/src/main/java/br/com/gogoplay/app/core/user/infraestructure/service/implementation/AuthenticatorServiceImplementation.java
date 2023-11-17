package br.com.gogoplay.app.core.user.infraestructure.service.implementation;

import br.com.gogoplay.app.core.user.domain.entities.AuthenticationDTO;
import br.com.gogoplay.app.core.user.domain.entities.LoginResponseDTO;
import br.com.gogoplay.app.core.user.domain.entities.RegisterDTO;
import br.com.gogoplay.app.core.user.infraestructure.security.TokenService;
import br.com.gogoplay.app.core.user.infraestructure.database.UserDataBase;
import br.com.gogoplay.app.core.user.infraestructure.database.implementation.UserRepository;
import br.com.gogoplay.app.core.user.infraestructure.service.AuthenticatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth", produces = {"application/json"})
@Tag(name = "Gogo Play")
public class AuthenticatorServiceImplementation implements AuthenticatorService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Operation(summary = "Faz o login na aplicação.", method = "POST")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Login feito com sucesso."),
            @ApiResponse(responseCode = "400", description = "Informações informadas inválida."),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado."),
            @ApiResponse(responseCode = "403", description = "Usuário sem permissão."),
            @ApiResponse(responseCode = "500", description = "Erro ao tentar logar no sistema."),
    })
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        if(auth.isAuthenticated()){
            var token = tokenService.generateToken((UserDataBase)auth.getPrincipal());
            return ResponseEntity.ok(new LoginResponseDTO(token));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Informações de login informado, são inválidos.");
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.userRepository.findByLoginUserDetailed(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UserDataBase newUser = new UserDataBase(data.login(), encryptedPassword, data.role(), data.name());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
