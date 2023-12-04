package br.com.gogoplay.app.core.user.infra.database.implementation;

import br.com.gogoplay.app.core.user.domain.entities.UserRole;
import br.com.gogoplay.app.core.user.infra.database.UserDataBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Transactional
public interface UserRepository extends JpaRepository<UserDataBase, UUID> {
    UserDataBase findByUsername(String username);

    @Query("SELECT u FROM users u WHERE u.login = :login")
    UserDataBase findByLoginUserDataBase(String login);

    @Query("SELECT u FROM users u WHERE u.login = :login")
    UserDetails findByLoginUserDetailed(String login);

    @Query("SELECT u.id FROM users u WHERE u.login = :login")
    UUID getIdByLogin(String login);

    @Modifying
    @Transactional
    @Query("UPDATE users u SET u.birthDate = :birthDate, u.description = :description, u.name = :name, u.username = :userName, u.role = :userRole WHERE u.id = :userId")
    void updateUser(UUID userId, LocalDate birthDate, String description, String name, String userName, UserRole userRole);

    @Modifying
    @Transactional
    @Query("UPDATE users u SET u.password = :password WHERE u.id = :userId")
    void updateUserPassword(UUID userId, String password);
}
