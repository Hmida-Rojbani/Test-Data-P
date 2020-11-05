package de.tekup.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.tekup.project.models.PersonEntity;
import de.tekup.project.repositories.PersonRepository;

@Service
public class PersonServiceImpl {
	
	private PersonRepository repository;
	
	@Autowired
	public PersonServiceImpl(PersonRepository repository) {
		super();
		this.repository = repository;
	}
	
	// Retrieve all instance in database
	public List<PersonEntity> getAllEntities(){
		return repository.findAll();
	}
	
	// Save a request into Database 
	public PersonEntity savePerson(PersonEntity person) {
		return repository.save(person);
	}

}
