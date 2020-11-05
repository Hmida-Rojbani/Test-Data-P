package de.tekup.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tekup.project.models.PersonEntity;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

}
