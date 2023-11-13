package br.com.gogoplay.app.core.user.infraestructure.database;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_contact")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContactDataBase {

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
