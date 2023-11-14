package br.com.gogoplay.app.core.user.domain.usecase;

import br.com.gogoplay.app.core.user.infraestructure.database.UserDataBase;
import org.springframework.http.ResponseEntity;

public interface UserUseCase {

    ResponseEntity create(UserDataBase userModel);

    ResponseEntity update(UserDataBase userModel);

    ResponseEntity getUserByUserName(String username);

    ResponseEntity getUserByLogin(String login);
}
