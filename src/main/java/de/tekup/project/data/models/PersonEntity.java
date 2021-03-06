package de.tekup.project.data.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Entity
@Data
@Table(name = "person")
@EqualsAndHashCode(of = {"name","dateOfBirth"})
@ToString(of = {"id","name","dateOfBirth"})
public class PersonEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "personName", length = 50, nullable = false, unique = true)
	private String name;
	
	private LocalDate dateOfBirth;
	
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "address")
	private AddressEntity address;
	
	@OneToMany(mappedBy = "person",cascade = CascadeType.REMOVE)
	List<TelephoneNumberEntity> phones;
	
	@ManyToMany(mappedBy = "persons",cascade = CascadeType.REMOVE)
	List<GamesEntity> games;
	
	public int getAge() {
		return LocalDate.now().getYear()-dateOfBirth.getYear();
	}
	
	public String getPersonAddress() {
		return address.getNumber()+", "+address.getStreet()+", "
				+address.getCity()+".";
	}

}
