package br.com.gogoplay.app.implementation.game.Friend.infraestructure.database;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "friends")
public class FriendDataBase {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID uuid;

    private UUID friendOneUUID;
    private UUID friendTowUUID;
    private boolean isActive;
    private Integer situation;

    @CreationTimestamp
    private LocalDateTime modifiedAt;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
