package br.com.gogoplay.app.core.user.domain.usecase.implementation;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.gogoplay.app.core.user.domain.usecase.UserUseCase;
import br.com.gogoplay.app.core.user.infraestructure.database.UserDataBase;
import br.com.gogoplay.app.core.user.infraestructure.database.implementation.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserUseCaseImplementation implements UserUseCase{

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity createUser (
            UserDataBase userModel
    ){
        userModel.setPassword(BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray()));

        if (this.userRepository.findByUsername(userModel.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existente no banco de dados!!");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(this.userRepository.save(userModel));
    }

    @Override
    public ResponseEntity getUserByUserName(String username){
        return ResponseEntity.status(HttpStatus.OK).body(this.userRepository.findByUsername(username));
    }
}
