package br.com.gogoplay.app.core.user.infraestructure.service.implementation;

import br.com.gogoplay.app.core.user.domain.usecase.implementation.UserUseCaseImplementation;
import br.com.gogoplay.app.core.user.infraestructure.database.UserDataBase;
import br.com.gogoplay.app.core.user.infraestructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
            @RequestBody UserDataBase userModel
    ) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Obtenha o token de autenticação
        String authToken = null;
        if (authentication != null && authentication.getCredentials() != null) {
            authToken = authentication.getCredentials().toString();
        }
//        System.out.println("authentication: " + authentication);
        System.out.println("token: " + authToken);

        return userUseCase.update(userModel);
    }
}
