package de.tekup.project.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tekup.project.data.models.PersonEntity;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

}
