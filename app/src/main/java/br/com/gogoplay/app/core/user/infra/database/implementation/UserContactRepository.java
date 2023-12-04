package br.com.gogoplay.app.core.user.infra.database.implementation;

import br.com.gogoplay.app.core.user.infra.database.ContactDataBase;
import br.com.gogoplay.app.core.user.infra.database.UserDataBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
public interface UserContactRepository extends JpaRepository<ContactDataBase, UUID> {


    @Modifying
    @Query("DELETE FROM users_contact uc WHERE uc.user = :user")
    void deleteByUserID(UserDataBase user);
}
