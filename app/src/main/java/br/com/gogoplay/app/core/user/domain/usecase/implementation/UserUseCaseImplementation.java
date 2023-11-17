package br.com.gogoplay.app.core.user.domain.usecase.implementation;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.gogoplay.app.core.user.domain.usecase.UserUseCase;
import br.com.gogoplay.app.core.user.infraestructure.database.UserDataBase;
import br.com.gogoplay.app.core.user.infraestructure.database.implementation.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserUseCaseImplementation implements UserUseCase{

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity create (
            UserDataBase userModel
    ){
        userModel.setPassword(BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray()));

        if (this.userRepository.findByUsername(userModel.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existente no banco de dados!!");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(this.userRepository.save(userModel));
    }

    @Override
    public ResponseEntity update (
            UserDataBase userModel
    ){
        Optional<UserDataBase> user = this.userRepository.findById(userModel.getId());
        UserDataBase userName = this.userRepository.findByUsername(userModel.getUsername());

        if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id de usuário não corresponde a nenhum usuário cadastrado.");
        }
        System.out.println(userName);
        if(userName != null && userName.getId() != userModel.getId()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User name já cadastrado no sistema.");
        }

        userModel.setPassword(user.get().getPassword());
        userModel.setLogin(user.get().getLogin());
        userModel.setCreatedAt(user.get().getCreatedAt());
        userModel.setModifiedAt(LocalDateTime.now());
        userModel.setIsActive(true);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.userRepository.save(userModel));
    }

    @Override
    public ResponseEntity getUserByUserName(String username){
        return ResponseEntity.status(HttpStatus.OK).body(this.userRepository.findByUsername(username));
    }

    @Override
    public ResponseEntity getUserByLogin(String login){
        return ResponseEntity.status(HttpStatus.OK).body(this.userRepository.findByLoginUserDataBase(login));
    }
}
