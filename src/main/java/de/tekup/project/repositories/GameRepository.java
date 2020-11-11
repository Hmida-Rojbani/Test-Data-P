package de.tekup.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tekup.project.models.GamesEntity;

public interface GameRepository extends JpaRepository<GamesEntity, Integer>{

}
