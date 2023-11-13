package br.com.gogoplay.app.implementation.games.animals.infraestructure.database.implementation;

import br.com.gogoplay.app.implementation.games.animals.infraestructure.database.AnimalsDataBase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnimalsRepository extends JpaRepository<AnimalsDataBase, UUID> {

}
