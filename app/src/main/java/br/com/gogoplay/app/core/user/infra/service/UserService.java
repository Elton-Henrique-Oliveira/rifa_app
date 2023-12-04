package br.com.gogoplay.app.core.user.infra.service;

import br.com.gogoplay.app.core.user.domain.entities.AlterPasswordDTO;
import br.com.gogoplay.app.core.user.domain.entities.UserAlterDTO;
import br.com.gogoplay.app.core.user.domain.entities.UserCreateDTO;
import org.springframework.http.ResponseEntity;


public interface UserService {

    ResponseEntity<String> create(
            UserCreateDTO userModel,
            String authorizationHeader
    );

    ResponseEntity<String> update(
            UserAlterDTO userModel,
            String authorizationHeader
    );

    ResponseEntity<String> alterPassword(
            AlterPasswordDTO userModel,
            String authorizationHeader
    );
}

