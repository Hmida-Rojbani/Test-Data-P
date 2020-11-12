package de.tekup.project.data.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter@Setter
public class AddressEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int code;
	
	private int number;
	private String street;
	private String city;
	
	@OneToOne(mappedBy = "address")
	@JsonIgnore
	private PersonEntity person;

}
