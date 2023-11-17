package br.com.gogoplay.app.core.user.infraestructure.service.implementation;

import br.com.gogoplay.app.core.user.domain.entities.UserAlterDTO;
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
        return userUseCase.create(userModel);
    }

    @PutMapping("/")
    public ResponseEntity update(
            @RequestBody UserAlterDTO userModel,
            @RequestHeader(name = "Authorization") String authorizationHeader
    ) {
//
//        String authToken = "";
//
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            authToken = authorizationHeader.substring(7);
//            System.out.println("Token: " + authToken);
//        }

        return userUseCase.update(userModel);
    }
}
