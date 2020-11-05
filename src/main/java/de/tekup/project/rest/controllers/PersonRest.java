package de.tekup.project.rest.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.tekup.project.models.PersonEntity;
import de.tekup.project.services.PersonServiceImpl;

@RestController
@RequestMapping("/api/persons")
public class PersonRest {
	
	private PersonServiceImpl service;
	
	@Autowired
	public PersonRest(PersonServiceImpl service) {
		super();
		this.service = service;
	}
	
	@GetMapping
	public List<PersonEntity> getAll(){
		return service.getAllEntities();
	}
	
	@PostMapping
	public PersonEntity createPerson(@RequestBody PersonEntity personRequest) {
		return service.savePerson(personRequest);
	}
	
	@GetMapping("/{id}")
	public PersonEntity getById(@PathVariable("id") long id){
		return service.getPersonById(id);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	

}
