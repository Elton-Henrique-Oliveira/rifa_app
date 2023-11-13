package br.com.gogoplay.app.implementation.games.animals.infraestructure.database;

import br.com.gogoplay.app.implementation.games.animals.domain.entities.Animals;
import br.com.gogoplay.app.implementation.games.infraestructure.database.GameDataBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "animal_game")
public class AnimalsDataBase {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID uuid;

    private UUID user_id;

    private Integer game_code;

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

    public AnimalsDataBase(UUID user_id, String cpf, Animals animal, BigDecimal bet, BigDecimal must, int game_code){
        this.user_id = user_id;
        this.cpf = cpf;
        this.animal = animal;
        this.bet = bet;
        this.must = must;
        this.game_code = game_code;
    }
}
