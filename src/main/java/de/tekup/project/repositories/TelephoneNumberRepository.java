package de.tekup.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tekup.project.models.TelephoneNumberEntity;

public interface TelephoneNumberRepository extends JpaRepository<TelephoneNumberEntity, Integer>{

}
