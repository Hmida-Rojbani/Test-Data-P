package de.tekup.project.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequest {

	@NotBlank
	private String name;
	@Past
	private LocalDate dateOfBirth;

	private AddressRequest address;
}
