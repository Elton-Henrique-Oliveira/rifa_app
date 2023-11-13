package br.com.gogoplay.app.implementation.games.infraestructure.database;

import br.com.gogoplay.app.implementation.games.animals.domain.entities.Animals;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "event_game")
public class GameDataBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer code;

    private String observation;

    private Animals animal;

    private BigDecimal minValue;

    private BigDecimal maxValue;

    private Boolean finalized;

    private Integer prizeMultiply;

    private Animals drawAnimal;

    private LocalDateTime drawAt;

    @CreationTimestamp
    private LocalDateTime modifiedAt;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public GameDataBase(String observation, Animals animal, BigDecimal minValue, BigDecimal maxValue, boolean finalized, int prizeMultiply, LocalDateTime drawAt) {
        this.observation = observation;
        this.animal = animal;
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.finalized = finalized;
        this.prizeMultiply = prizeMultiply;
        this.drawAt = drawAt;
    }
}
