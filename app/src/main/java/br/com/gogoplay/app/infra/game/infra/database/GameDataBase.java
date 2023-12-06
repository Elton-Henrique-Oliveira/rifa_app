package br.com.gogoplay.app.infra.game.infra.database;

import br.com.gogoplay.app.infra.game.domain.entities.GameCreateDTO;
import br.com.gogoplay.app.infra.game.infra.database.implementation.GameTypeRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity(name = "game")
@EqualsAndHashCode(of = "id")
public class GameDataBase {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int code;

    @NotNull
    private String name;

    @NotNull
    private String description;

    private BigDecimal cost;

    private int prizeMultiplier;

    private BigDecimal prize;

    @ManyToOne
    @JoinColumn(name = "game_type_uuid")
    private GameTypeDataBase type;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime initialDate;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime finalDate;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime drawDate;

    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedAt;

    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    public GameDataBase(GameCreateDTO newGame) {
        this.name = newGame.name();
        this.description = newGame.description();
        this.cost = newGame.cost();
        this.prizeMultiplier = newGame.prizeMultiplier();
        this.prize = newGame.prize();
        this.initialDate = newGame.initialDate();
        this.finalDate = newGame.finalDate();
        this.drawDate = newGame.drawDate();
        this.type = newGame.type();
    }
}
