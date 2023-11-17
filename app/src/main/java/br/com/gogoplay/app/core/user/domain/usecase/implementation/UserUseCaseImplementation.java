package br.com.gogoplay.app.core.user.domain.usecase.implementation;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.gogoplay.app.core.user.domain.entities.*;
import br.com.gogoplay.app.core.user.domain.usecase.UserUseCase;
import br.com.gogoplay.app.core.user.infraestructure.database.ContactDataBase;
import br.com.gogoplay.app.core.user.infraestructure.database.UserDataBase;
import br.com.gogoplay.app.core.user.infraestructure.database.implementation.UserContactRepository;
import br.com.gogoplay.app.core.user.infraestructure.database.implementation.UserRepository;
import br.com.gogoplay.app.core.user.infraestructure.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UserUseCaseImplementation implements UserUseCase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserContactRepository userContactRepository;

    @Autowired
    TokenService tokenService;

    @Override
    public ResponseEntity create(
            UserCreateDTO userModel,
            String authToken
    ) {

        if (!authToken.isEmpty() && !authToken.isBlank()) {
            var login = tokenService.validateToken(authToken);

            UserDataBase userLogin = userRepository.findByLoginUserDataBase(login);

            if (!verifyPermissionRoleRegisterAndLoginUser(userLogin.getRole(), UserRole.getByRole(userModel.role()))) {
                return ResponseEntity.status(HttpStatus.OK).body("Role não liberada para esse usuário alterar.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Usuario sem permissão para cadastrar outro usuário.");
        }

        if (userModel.login().isEmpty() || userModel.login().isBlank()) {
            return ResponseEntity.status(HttpStatus.OK).body("Usuario deve ser informado");
        }

        if (userModel.password() != null && (!userModel.password().isEmpty() || !userModel.password().isBlank())) {
            return ResponseEntity.status(HttpStatus.OK).body("Senha não deve ser informada\npor padrão ela é gerada {login} + 123 e pode ser alterada pelo usuário após login.");
        }

        if (userModel.role().isBlank() || userModel.role().isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("Nível de permissão deve ser informado.");
        }

        String passwordNew = BCrypt.withDefaults().hashToString(12, (userModel.login() + "123").toCharArray());
        UserRole userRole = UserRole.getByRole(userModel.role());
        List<ContactDataBase> contacts = new ArrayList<>();

        UserDataBase newUser = new UserDataBase();
        newUser.setId(UUID.randomUUID());
        newUser.setBirthDate(userModel.birthDate());
        newUser.setDescription(userModel.description());
        newUser.setUsername(userModel.userName());
        newUser.setName(userModel.name());
        newUser.setRole(userRole);
        newUser.setLogin(userModel.login());
        newUser.setPassword(passwordNew);
        newUser.setIsActive(true);
        newUser.setModifiedAt(LocalDateTime.now());
        newUser.setCreatedAt(LocalDateTime.now());
        userRepository.save(newUser);

        for (UserContactDTO contact : userModel.contacts()) {
            ContactDataBase userContact = new ContactDataBase();
            userContact.setUuid(UUID.randomUUID());
            userContact.setLabel(contact.label());
            userContact.setType(contact.type());
            userContact.setUser(newUser);
            userContact.setStatusCode(0);
            userContact.setModifiedAt(LocalDateTime.now());
            userContact.setCreatedAt(LocalDateTime.now());
            userContactRepository.save(userContact);

            contacts.add(userContact);
        }

        newUser.setContacts(contacts);

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso.");
    }

    @Override
    public ResponseEntity update(
            UserAlterDTO userModel,
            String authToken
    ) {

        if (!authToken.isEmpty() && !authToken.isBlank()) {
            var login = tokenService.validateToken(authToken);

            UserDataBase userLogin = userRepository.findByLoginUserDataBase(login);

            if (!verifyPermissionRoleRegisterAndLoginUser(userLogin.getRole(), UserRole.getByRole(userModel.role()))) {
                return ResponseEntity.status(HttpStatus.OK).body("Role não liberada para esse usuário alterar.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Usuario sem permissão para alterar outro usuário.");
        }

        Optional<UserDataBase> user = userRepository.findById(userModel.id());
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("Usuário não encontrado no banco de dados.");
        }
        System.out.println(userModel.userName());
        if (userModel.userName() == null || userModel.userName().isEmpty() || userModel.userName().isBlank()) {
            return ResponseEntity.status(HttpStatus.OK).body("Nome de usuário deve ser informado.");
        }
        if (userModel.name() == null || userModel.name().isEmpty() || userModel.name().isBlank()) {
            return ResponseEntity.status(HttpStatus.OK).body("Nome deve ser informado.");
        }

        UserRole userRole = UserRole.getByRole(userModel.role());

        userRepository.updateUser(userModel.id(), userModel.birthDate(), userModel.description(), userModel.name(), userModel.userName(), userRole);
        userContactRepository.deleteByUserID(user.get());

        for (UserContactDTO contact : userModel.contacts()) {
            ContactDataBase userContact = new ContactDataBase();
            userContact.setUuid(UUID.randomUUID());
            userContact.setLabel(contact.label());
            userContact.setType(contact.type());
            userContact.setUser(user.get());
            userContact.setStatusCode(0);
            userContact.setModifiedAt(LocalDateTime.now());
            userContact.setCreatedAt(LocalDateTime.now());
            userContactRepository.save(userContact);
        }

        return ResponseEntity.status(HttpStatus.OK).body("Usuário alterado com sucesso.");
    }

    @Override
    public ResponseEntity alterPassword(
            AlterPasswordDTO alterPassword,
            String authToken
    ){

        if (!authToken.isEmpty() && !authToken.isBlank()) {
            var login = tokenService.validateToken(authToken);

            UserDataBase userLogin = userRepository.findByLoginUserDataBase(login);

            var usernamePassword = new UsernamePasswordAuthenticationToken(userLogin, alterPassword.oldPassword());

            if(!usernamePassword.isAuthenticated()){
                return ResponseEntity.status(HttpStatus.OK).body("Senha anterior não está correta.");
            }

            String encryptedPassword = new BCryptPasswordEncoder().encode(alterPassword.newPassword());

            if(!alterPassword.oldPassword().trim().equals(encryptedPassword)){
                return ResponseEntity.status(HttpStatus.OK).body("Senha anterior é igual a atual.");
            }

            userRepository.updateUserPassword(userLogin.getId(), encryptedPassword);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Senha alterada com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Usuário não authenticado.");
        }
    }

    @Override
    public boolean verifyPermissionRoleRegisterAndLoginUser(UserRole roleUserLogin, UserRole roleUserRegister) {

        int userRegisterRole = UserRole.getCodeByRole(roleUserRegister.getRole());
        int userLoginRole = UserRole.getCodeByRole(roleUserLogin.getRole());

        return userRegisterRole >= userLoginRole;
    }
}
