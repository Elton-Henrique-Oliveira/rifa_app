package br.com.gogoplay.app.core.user.domain.usecase;

import br.com.gogoplay.app.core.user.domain.entities.AlterPasswordDTO;
import br.com.gogoplay.app.core.user.domain.entities.UserAlterDTO;
import br.com.gogoplay.app.core.user.domain.entities.UserCreateDTO;
import br.com.gogoplay.app.core.user.domain.entities.UserRole;
import org.springframework.http.ResponseEntity;

public interface UserUseCase {

    ResponseEntity create(UserCreateDTO userModel, String authToken);

    ResponseEntity update(UserAlterDTO userModel, String authToken);

    ResponseEntity alterPassword(AlterPasswordDTO alterPassword, String authToken);

    boolean verifyPermissionRoleRegisterAndLoginUser(UserRole roleUserLogin, UserRole roleUserRegister);
}
