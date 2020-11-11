package de.tekup.project.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter@Setter
@EqualsAndHashCode(of = {"type","title"})
public class GamesEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String title;
	private String type;
	
	@ManyToMany
	@JoinTable(name = "PlayedBy")
	@JsonIgnore
	private List<PersonEntity> persons;
	
	

}
