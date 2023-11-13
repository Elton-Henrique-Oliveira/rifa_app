package br.com.gogoplay.app.core.user.infraestructure.service.implementation;

import br.com.gogoplay.app.core.user.domain.usecase.UserUseCase;
import br.com.gogoplay.app.core.user.domain.usecase.implementation.UserUseCaseImplementation;
import br.com.gogoplay.app.core.user.infraestructure.database.UserDataBase;
import br.com.gogoplay.app.core.user.infraestructure.service.UserService;
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
            @RequestBody UserDataBase userModel
    ) {
        return userUseCase.createUser(userModel);
    }

    @GetMapping("/{login}")
    public ResponseEntity getUser(
            @PathVariable String login
    ) {
        return userUseCase.getUserByLogin(login);
    }
}
