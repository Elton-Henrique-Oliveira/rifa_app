package br.com.gogoplay.app.core.user.infraestructure.service;

import br.com.gogoplay.app.core.user.domain.entities.AlterPasswordDTO;
import br.com.gogoplay.app.core.user.domain.entities.UserAlterDTO;
import br.com.gogoplay.app.core.user.domain.entities.UserCreateDTO;
import org.springframework.http.ResponseEntity;


public interface UserService {

    ResponseEntity create(
            UserCreateDTO userModel,
            String authorizationHeader
    );

    ResponseEntity update(
            UserAlterDTO userModel,
            String authorizationHeader
    );

    ResponseEntity alterPassword(
            AlterPasswordDTO userModel,
            String authorizationHeader
    );
}

