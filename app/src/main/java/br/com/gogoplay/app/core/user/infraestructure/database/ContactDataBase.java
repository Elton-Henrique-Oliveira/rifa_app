package br.com.gogoplay.app.core.user.infraestructure.database;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "users_contact")
@Table(name = "users_contact")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContactDataBase {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID uuid;

    private String label;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "users_contact_type_id")
    private ContactTypeDataBase type;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private UserDataBase user;

    private Integer statusCode;

    @CreationTimestamp
    private LocalDateTime modifiedAt;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
