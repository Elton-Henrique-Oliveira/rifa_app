package br.com.gogoplay.app.implementation.games.animals.infraestructure.database;

import br.com.gogoplay.app.core.user.domain.entities.UserRole;
import br.com.gogoplay.app.core.user.infraestructure.database.UserDataBase;
import br.com.gogoplay.app.implementation.games.animals.domain.entities.Animals;
import br.com.gogoplay.app.implementation.games.infraestructure.database.GameDataBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "animal_game")
public class AnimalsDataBase {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID uuid;

    @ManyToOne
    private UserDataBase user;

    @ManyToOne
    private GameDataBase game;

    private String cpf;

    private Animals animal;

    private BigDecimal bet;

    private BigDecimal prize;

    private BigDecimal must;

    private Boolean isPaid;

    @CreationTimestamp
    private LocalDateTime modifiedAt;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public AnimalsDataBase(UserDataBase user, String cpf, Animals animal, BigDecimal bet, BigDecimal must, GameDataBase game){
        this.user = user;
        this.cpf = cpf;
        this.animal = animal;
        this.bet = bet;
        this.must = must;
        this.game = game;
    }
}
