package br.com.gogoplay.app.core.user.domain.usecase;

import br.com.gogoplay.app.core.user.domain.entities.UserAlterDTO;
import br.com.gogoplay.app.core.user.infraestructure.database.UserDataBase;
import org.springframework.http.ResponseEntity;

public interface UserUseCase {

    ResponseEntity create(UserDataBase userModel);

    ResponseEntity update(UserAlterDTO userModel);

    ResponseEntity getUserByUserName(String username);

    ResponseEntity getUserByLogin(String login);
}
