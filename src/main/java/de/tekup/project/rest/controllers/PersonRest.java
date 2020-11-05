package de.tekup.project.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	

}
