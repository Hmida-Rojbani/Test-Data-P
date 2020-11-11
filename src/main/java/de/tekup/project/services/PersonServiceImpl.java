package de.tekup.project.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.tekup.project.models.AddressEntity;
import de.tekup.project.models.GamesEntity;
import de.tekup.project.models.PersonEntity;
import de.tekup.project.models.TelephoneNumberEntity;
import de.tekup.project.repositories.AddressRepository;
import de.tekup.project.repositories.GameRepository;
import de.tekup.project.repositories.PersonRepository;
import de.tekup.project.repositories.TelephoneNumberRepository;

@Service
public class PersonServiceImpl {

	private PersonRepository reposPerson;
	private AddressRepository reposAddress;
	private TelephoneNumberRepository reposPhone;
	private GameRepository reposGame;

	@Autowired
	public PersonServiceImpl(PersonRepository repository, AddressRepository reposAddress,
			TelephoneNumberRepository reposPhone, GameRepository reposGame) {
		super();
		this.reposPerson = repository;
		this.reposAddress = reposAddress;
		this.reposPhone = reposPhone;
		this.reposGame = reposGame;
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
		/*
		 * One Way for (TelephoneNumberEntity phone : phones) {
		 * phone.setPerson(personInBase); reposPhone.save(phone); }
		 */
		// second Way
		phones.forEach(phone -> phone.setPerson(personInBase));
		reposPhone.saveAll(phones);

		// TODO save Games;
		List<GamesEntity> games = personRequest.getGames();
		List<GamesEntity> gamesInBase = reposGame.findAll();
		for (GamesEntity game : games) {
			if(gamesInBase.contains(game)) {
				int indexOfGameInList = gamesInBase.indexOf(game);
				GamesEntity gameInbase = gamesInBase.get(indexOfGameInList);
				// add the new person to the existing list of persons
				gameInbase.getPersons().add(personInBase);
				reposGame.save(gameInbase);
			}else {
				// save game for the first time
				game.setPersons(Arrays.asList(personInBase));
				reposGame.save(game);
			}
		}

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
		// There is a better way (3 points DS Bonus)
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

		//  Update phone
		List<TelephoneNumberEntity> oldPhones = oldPerson.getPhones();
		List<TelephoneNumberEntity> newPhones = newPerson.getPhones();
		if (newPhones != null) {
			for (TelephoneNumberEntity newPhone : newPhones) {
				for (TelephoneNumberEntity oldPhone : oldPhones) {
					if (newPhone.getId() == oldPhone.getId()) {
						if (newPhone.getNumber() != null)
							oldPhone.setNumber(newPhone.getNumber());
						if (newPhone.getOperator() != null)
							oldPhone.setOperator(newPhone.getOperator());
					}
				}
			}
		}

		// TODO Update Games
		List<GamesEntity> oldGames = oldPerson.getGames();
		List<GamesEntity> newGames = newPerson.getGames();
		if(newGames != null) {
			for (GamesEntity newGame : newGames) {
				for (GamesEntity oldGame : oldGames) {
					if(newGame.getId()==oldGame.getId()) {
						if(newGame.getTitle() != null)
							oldGame.setTitle(newGame.getTitle());
						if(newGame.getType() != null)
							oldGame.setType(newGame.getType());
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
	
	// All person with a given operator
	public List<PersonEntity> getAllByOperator(String operator){
		// version 1 simple
		/*
		Set<PersonEntity> persons = new HashSet<>();
		List<TelephoneNumberEntity> phones = reposPhone.findAll();
		
		for (TelephoneNumberEntity phone : phones) {
			if(phone.getOperator().equalsIgnoreCase(operator)) {
				// persons is List
				// if(! persons.contains(phone.getPerson())) {
				//	persons.add(phone.getPerson());
				//}
				 
				persons.add(phone.getPerson());
			}
		}	
		
		return new ArrayList<>(persons);
		*/
		// Version 2 ( Java 8)
		List<PersonEntity> persons = 
				reposPhone.findAll()
						.stream()
						.filter(phone -> phone.getOperator().equalsIgnoreCase(operator))
						.map(phone -> phone.getPerson())
						.distinct()
						.collect(Collectors.toList());
												
		
		return persons ;
	}
	
	// Average age of all Persons
	
	//Persons who play the type of game the most played.
	

	// Display the games type and the number of games for each type;
	
	// return a person by name 

}
