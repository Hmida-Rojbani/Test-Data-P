package de.tekup.project.data.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import de.tekup.project.data.models.PersonEntity;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

	// find by name exactly
	Optional<PersonEntity> findByName(String name);

	// find by Name
	Optional<PersonEntity> findByNameIgnoreCase(String name);

	// With Query JPQL
	// @Query("select p from PersonEntity p "
	// + "where p.name = ?1")
	// Optional<PersonEntity> getName(String name);
	@Query("select p from PersonEntity p " + "where p.name = :n")
	Optional<PersonEntity> getName(@Param("n") String name);

	// get sans Maj
	@Query("select p from PersonEntity p " + 
			"where lower(p.name) = lower(:n)")
	Optional<PersonEntity> getNameIgCase(@Param("n") String name);
}
