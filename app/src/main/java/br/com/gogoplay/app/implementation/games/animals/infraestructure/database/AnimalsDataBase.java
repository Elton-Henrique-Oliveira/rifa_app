package br.com.gogoplay.app.implementation.games.animals.infraestructure.database;

import br.com.gogoplay.app.implementation.games.animals.domain.entities.Animals;
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
@Entity(name = "jogo_bicho")
public class AnimalsDataBase {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID uuid;

    private UUID user_id;

    //jogo

    private String cpf;

    private Animals animal;

    private BigDecimal aposta;

    private BigDecimal premio;

    private BigDecimal deve;

    private Boolean pago;

    @CreationTimestamp
    private LocalDateTime modifiedAt;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public AnimalsDataBase(UUID user_id, String cpf, Animals bicho, BigDecimal aposta, BigDecimal deve){
        this.user_id = user_id;
        this.cpf = cpf;
        this.animal = bicho;
        this.aposta = aposta;
        this.deve = deve;
    }
}
