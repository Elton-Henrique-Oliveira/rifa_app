package br.com.gogoplay.app.core.user.infraestructure.service.implementation;

import br.com.gogoplay.app.core.user.domain.entities.UserAlterDTO;
import br.com.gogoplay.app.core.user.domain.entities.UserCreateDTO;
import br.com.gogoplay.app.core.user.domain.usecase.implementation.UserUseCaseImplementation;
import br.com.gogoplay.app.core.user.infraestructure.service.UserService;
import br.com.gogoplay.app.core.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserUseCaseImplementation userUseCase;

    @PostMapping("/")
    public ResponseEntity create(
            @RequestBody UserCreateDTO userModel,
            @RequestHeader(name = "Authorization") String authorizationHeader
    ) {

        String authToken = TokenUtils.extractToken(authorizationHeader);
        return userUseCase.create(userModel, authToken);
    }

    @PutMapping("/")
    public ResponseEntity update(
            @RequestBody UserAlterDTO userModel,
            @RequestHeader(name = "Authorization") String authorizationHeader
    ) {

        String authToken = TokenUtils.extractToken(authorizationHeader);

        return userUseCase.update(userModel, authToken);
    }
}
