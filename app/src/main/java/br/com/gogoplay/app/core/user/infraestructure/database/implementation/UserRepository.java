package br.com.gogoplay.app.core.user.infraestructure.database.implementation;

import br.com.gogoplay.app.core.user.infraestructure.database.UserDataBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserDataBase, UUID> {
    UserDataBase findByUsername(String username);

    @Query("SELECT u FROM users u WHERE u.login = :login")
    UserDataBase findByLoginUserDataBase(String login);

    @Query("SELECT u FROM users u WHERE u.login = :login")
    UserDetails findByLoginUserDetailed(String login);

}
