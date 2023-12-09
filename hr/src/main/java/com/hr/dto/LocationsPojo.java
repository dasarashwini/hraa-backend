package com.hr.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data

public class LocationsPojo {
	@NotNull(message = "Validations Failed.........")
//	@NotBlank(message = "Validations Failed.........")
	@Min(value=1,message = "Locations ID must not be empty ")
	private int locationsId;
	
	@NotNull(message = "Address should not be null.........")
	@NotBlank(message = "Address should not be null.........")
	private String streetAddress;
	
	@NotNull(message = "Validations Failed.........")
	@NotEmpty(message = "Validations Failed.........")
	private String postalCode;
	
	@NotNull(message = "Validations Failed.........")
	@NotEmpty(message = "Validations Failed.........")
	private String city;
	
	@NotEmpty(message = "Validations Failed.........")
	@NotNull(message = "Validations Failed.........")
	private String stateProvince;
	
	private CountriesPojo countriesPojo;
	
}
