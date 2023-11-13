package br.com.gogoplay.app.core.user.infraestructure.service;


import br.com.gogoplay.app.core.user.domain.entities.AuthenticationDTO;
import br.com.gogoplay.app.core.user.domain.entities.RegisterDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthenticatorService {

    ResponseEntity login(@RequestBody @Valid AuthenticationDTO data);

    ResponseEntity register(@RequestBody @Valid RegisterDTO data);

}
