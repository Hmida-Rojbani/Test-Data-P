package de.tekup.project.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tekup.project.data.models.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer>{

}
