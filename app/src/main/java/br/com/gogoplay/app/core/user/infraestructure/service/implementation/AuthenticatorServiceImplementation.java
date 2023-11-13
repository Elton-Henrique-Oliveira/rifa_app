package br.com.gogoplay.app.core.user.infraestructure.service.implementation;

import br.com.gogoplay.app.core.user.domain.entities.AuthenticationDTO;
import br.com.gogoplay.app.core.user.domain.entities.LoginResponseDTO;
import br.com.gogoplay.app.core.user.domain.entities.RegisterDTO;
import br.com.gogoplay.app.core.user.infra.security.TokenService;
import br.com.gogoplay.app.core.user.infraestructure.database.UserDataBase;
import br.com.gogoplay.app.core.user.infraestructure.database.implementation.UserRepository;
import br.com.gogoplay.app.core.user.infraestructure.service.AuthenticatorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticatorServiceImplementation implements AuthenticatorService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserDataBase)auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.userRepository.findByLoginUserDetailed(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UserDataBase newUser = new UserDataBase(data.login(), encryptedPassword, data.role(), data.name());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
