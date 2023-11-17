package br.com.gogoplay.app.core.user.infraestructure.database;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity(name = "users_contact_type")
@Table(name = "users_contact_type")
@EqualsAndHashCode(of = "id")
public class ContactTypeDataBase {

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
