package de.tekup.project.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tekup.project.data.models.GamesEntity;

public interface GameRepository extends JpaRepository<GamesEntity, Integer>{

}
