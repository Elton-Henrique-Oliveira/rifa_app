package br.com.gogoplay.app.implementation.game.infraestructure.database;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "game")
public class GameDataBase {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID uuid;
}
