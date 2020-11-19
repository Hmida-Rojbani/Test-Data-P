package de.tekup.project.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimplePerson {

	private long id;

	private String name;

	private int age;
	
	private String personAddress;
}
