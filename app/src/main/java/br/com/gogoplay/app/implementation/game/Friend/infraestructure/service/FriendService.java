package br.com.gogoplay.app.implementation.game.Friend.infraestructure.service;

import br.com.gogoplay.app.implementation.game.Friend.domain.usecases.FriendUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/friend")
public class FriendService {

    @Autowired
    private FriendUseCase friendUseCase;

    @GetMapping("/")
    public ResponseEntity getFriendsByUUID(){

        return friendUseCase.getFriendsByUUID();
    }
}
