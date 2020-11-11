package de.tekup.project.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.tekup.project.models.AddressEntity;
import de.tekup.project.models.PersonEntity;
import de.tekup.project.models.TelephoneNumberEntity;
import de.tekup.project.repositories.AddressRepository;
import de.tekup.project.repositories.PersonRepository;
import de.tekup.project.repositories.TelephoneNumberRepository;

@Service
public class PersonServiceImpl {

	private PersonRepository reposPerson;
	private AddressRepository reposAddress;
	private TelephoneNumberRepository reposPhone;

	@Autowired
	public PersonServiceImpl(PersonRepository repository, 
			AddressRepository reposAddress, TelephoneNumberRepository reposPhone) {
		super();
		this.reposPerson = repository;
		this.reposAddress = reposAddress;
		this.reposPhone = reposPhone;
	}

	// Retrieve all instance in database
	public List<PersonEntity> getAllEntities() {
		return reposPerson.findAll();
	}

	// Save a request into Database
	public PersonEntity savePerson(PersonEntity personRequest) {
		// save Address
		AddressEntity address = personRequest.getAddress();
		reposAddress.save(address);
		// save Person
		PersonEntity personInBase = reposPerson.save(personRequest);
		// save Phones
		List<TelephoneNumberEntity> phones = personRequest.getPhones();
		/* One Way
		 * for (TelephoneNumberEntity phone : phones) { 
			phone.setPerson(personInBase);
			reposPhone.save(phone);
		}
		*/
		// second Way
		phones.forEach(phone -> phone.setPerson(personInBase));
		reposPhone.saveAll(phones);
		return personInBase;
	}

	// Get Person By Id
	public PersonEntity getPersonById(long id) {

		Optional<PersonEntity> opt = reposPerson.findById(id);
		PersonEntity person = null;
		/*
		 * if(opt.isPresent()) person = opt.get(); else throw new
		 * NoSuchElementException("Person with this id is " + "not found");
		 */
		person = opt.orElseThrow(() -> new NoSuchElementException("Person " + "with this id is not found"));
		return person;
	}

	// modify a person ( with Address)
	public PersonEntity modifyPersonById(long id, PersonEntity newPerson) {
		PersonEntity oldPerson = this.getPersonById(id);
		if (newPerson.getName() != null)
			oldPerson.setName(newPerson.getName());
		if (newPerson.getDateOfBirth() != null)
			oldPerson.setDateOfBirth(newPerson.getDateOfBirth());
		// Update Address
		AddressEntity newAddress = newPerson.getAddress();
		AddressEntity oldAddress = oldPerson.getAddress();
		if (oldAddress == null)
			oldPerson.setAddress(newAddress);
		else {
			if (newAddress != null) {
				if (newAddress.getNumber() != 0)
					oldAddress.setNumber(newAddress.getNumber());
				if (newAddress.getStreet() != null)
					oldAddress.setStreet(newAddress.getStreet());
				if (newAddress.getCity() != null)
					oldAddress.setCity(newAddress.getCity());
			}
		}
		
		// TODO Update phone
		List<TelephoneNumberEntity> oldPhones = oldPerson.getPhones();
		List<TelephoneNumberEntity> newPhones = newPerson.getPhones();
		if(newPhones != null) {
			for (TelephoneNumberEntity newPhone : newPhones) {
				for (TelephoneNumberEntity oldPhone : oldPhones) {
					if(newPhone.getId() == oldPhone.getId()) {
						if(newPhone.getNumber() != null)
							oldPhone.setNumber(newPhone.getNumber());
						if(newPhone.getOperator() != null)
							oldPhone.setOperator(newPhone.getOperator());
					}
				}
			}
		}

		return reposPerson.save(oldPerson);
	}

	// delete person
	public PersonEntity deletePersonById(long id) {
		PersonEntity oldPerson = this.getPersonById(id);
		reposPerson.deleteById(id);
		return oldPerson;
	}

}
