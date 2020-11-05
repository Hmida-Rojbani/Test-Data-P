package de.tekup.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tekup.project.models.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer>{

}
