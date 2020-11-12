package de.tekup.project.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tekup.project.data.models.TelephoneNumberEntity;

public interface TelephoneNumberRepository extends JpaRepository<TelephoneNumberEntity, Integer>{

}
