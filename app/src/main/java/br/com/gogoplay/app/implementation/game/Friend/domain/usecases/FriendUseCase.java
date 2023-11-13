package br.com.gogoplay.app.implementation.game.Friend.domain.usecases;

import br.com.gogoplay.app.implementation.game.Friend.infraestructure.database.implementation.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class FriendUseCase {

    @Autowired
    private FriendRepository friendRepository;

    public ResponseEntity getFriendsByUUID(){
        return ResponseEntity.status(HttpStatus.OK).body(this.friendRepository.findAll( ));
    }
}
