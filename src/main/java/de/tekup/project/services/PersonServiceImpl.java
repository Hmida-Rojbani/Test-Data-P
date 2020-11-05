package de.tekup.project.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.apache.tomcat.jni.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.tekup.project.models.AddressEntity;
import de.tekup.project.models.PersonEntity;
import de.tekup.project.repositories.AddressRepository;
import de.tekup.project.repositories.PersonRepository;

@Service
public class PersonServiceImpl {

	private PersonRepository reposPerson;
	private AddressRepository reposAddress;

	@Autowired
	public PersonServiceImpl(PersonRepository repository, AddressRepository reposAddress) {
		super();
		this.reposPerson = repository;
		this.reposAddress = reposAddress;
	}

	// Retrieve all instance in database
	public List<PersonEntity> getAllEntities() {
		return reposPerson.findAll();
	}

	// Save a request into Database
	public PersonEntity savePerson(PersonEntity person) {
		AddressEntity address = person.getAddress();
		reposAddress.save(address);
		return reposPerson.save(person);
	}

	// Get Person By Id
	public PersonEntity getPersonById(long id) {
		
		Optional<PersonEntity> opt = reposPerson.findById(id);
		PersonEntity person = null;
		/*if(opt.isPresent())
			person = opt.get();
		else
			throw new NoSuchElementException("Person with this id is "
					+ "not found");*/
		person = opt.orElseThrow(()->new NoSuchElementException("Person "
				+ "with this id is not found"));
		return person;
	}
	
	// modify a person
	public PersonEntity modifyPersonById(long id, PersonEntity newPerson) {
		PersonEntity oldPerson = this.getPersonById(id);
		if(newPerson.getName() != null)
			oldPerson.setName(newPerson.getName());
		if(newPerson.getDateOfBirth() != null)
			oldPerson.setDateOfBirth(newPerson.getDateOfBirth());
		if(newPerson.getAddress() != null)
			oldPerson.setAddress(newPerson.getAddress());
		return reposPerson.save(oldPerson);
	}
	// delete person
	public PersonEntity deletePersonById(long id) {
		PersonEntity oldPerson = this.getPersonById(id);
		reposPerson.deleteById(id);
		return oldPerson;
	}

}
