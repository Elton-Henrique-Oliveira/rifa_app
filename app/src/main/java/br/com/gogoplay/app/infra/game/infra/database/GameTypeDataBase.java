package br.com.gogoplay.app.infra.game.infra.database;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "game_type")
@EqualsAndHashCode(of = "uuid")
public class GameTypeDataBase {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID uuid;

    private String label;

    private Integer code;

    private Integer statusCode;

    @CreationTimestamp
    private LocalDateTime modifiedAt;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
