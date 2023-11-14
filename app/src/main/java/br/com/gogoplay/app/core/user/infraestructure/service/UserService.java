package br.com.gogoplay.app.core.user.infraestructure.service;

import br.com.gogoplay.app.core.user.infraestructure.database.UserDataBase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public interface UserService {

    ResponseEntity create(
            UserDataBase userModel
    );

    ResponseEntity update(
            UserDataBase userModel
    );

    ResponseEntity getUser(
            String name
    );
}
