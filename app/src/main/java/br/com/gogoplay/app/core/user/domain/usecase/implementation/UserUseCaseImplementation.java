package br.com.gogoplay.app.core.user.domain.usecase.implementation;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.gogoplay.app.core.user.domain.entities.UserAlterDTO;
import br.com.gogoplay.app.core.user.domain.entities.UserContactDTO;
import br.com.gogoplay.app.core.user.domain.entities.UserRole;
import br.com.gogoplay.app.core.user.domain.usecase.UserUseCase;
import br.com.gogoplay.app.core.user.infraestructure.database.ContactDataBase;
import br.com.gogoplay.app.core.user.infraestructure.database.ContactTypeDataBase;
import br.com.gogoplay.app.core.user.infraestructure.database.UserDataBase;
import br.com.gogoplay.app.core.user.infraestructure.database.implementation.UserContactRepository;
import br.com.gogoplay.app.core.user.infraestructure.database.implementation.UserRepository;
import br.com.gogoplay.app.core.user.infraestructure.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserUseCaseImplementation implements UserUseCase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserContactRepository userContactRepository;

    @Override
    public ResponseEntity create(
            UserDataBase userModel
    ) {
        userModel.setPassword(BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray()));

        if (this.userRepository.findByUsername(userModel.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existente no banco de dados!!");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(this.userRepository.save(userModel));
    }

    @Override
    public ResponseEntity update(
            UserAlterDTO userModel
    ) {

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

        userRepository.updateUser(userModel.id(), userModel.birthDate(), userModel.description(), userModel.name(), userModel.userName());
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
    public ResponseEntity getUserByUserName(String username) {
        return ResponseEntity.status(HttpStatus.OK).body(this.userRepository.findByUsername(username));
    }

    @Override
    public ResponseEntity getUserByLogin(String login) {
        return ResponseEntity.status(HttpStatus.OK).body(this.userRepository.findByLoginUserDataBase(login));
    }
}
